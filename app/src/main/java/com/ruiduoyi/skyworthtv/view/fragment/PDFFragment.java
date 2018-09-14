package com.ruiduoyi.skyworthtv.view.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.contact.PDFFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.PDFFragmentBean;
import com.ruiduoyi.skyworthtv.model.net.PDFDownLoad;
import com.ruiduoyi.skyworthtv.presentor.PDFFragmentPresentor;
import com.ruiduoyi.skyworthtv.view.activity.BaseFragment;
import com.shockwave.pdfium.util.SizeF;

import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 显示pdf文档
 */
public class PDFFragment extends BaseFragment implements PDFFragmentContact.View {
    private long showTime = 0L;
    private static final String TAG = PDFFragment.class.getSimpleName();
    @BindView(R.id.pdf_pdfView_pdfFragment)
    PDFView pdfView;
    @BindView(R.id.tv_title_pdfFragment)
    TextView tvTitle;
    private View mRootView;
    private Unbinder unbinder;
    private PDFFragmentPresentor presentor;
    private ScheduledThreadPoolExecutor executors = new ScheduledThreadPoolExecutor(2);
    private Thread thread = new Thread();
    private int currFileIndex = 0;//当前展示的pdf文件（可能有多个文件）
    private boolean isEnd = true;
    private PDFFragmentBean data;
    public PDFFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(String devId,String funcId,String changeTime, String reflushTime) {
        PDFFragment fragment = new PDFFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.DEV_ID,devId);
        bundle.putString(BaseFragment.FUNC_ID,funcId);
        bundle.putString(BaseFragment.CHANGETIME,changeTime);
        bundle.putString(BaseFragment.REFLUSHTIME,reflushTime);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_pdf, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        //initPdfData();
        presentor  = new PDFFragmentPresentor(getContext(),this);
        presentor.loadData(devId,funcId);
        return mRootView;
    }

    /**
     * 把刷新数据的逻辑放这里，父类会每隔一段时间回调此方法，并且自动计算结束时间，切换到下一个fragment
     */
    @Override
    protected void load() {
        super.load();
        try {
            //如果已经展示完了，并且还有下一个文件，那么加载新的文件
            if (data == null){
                return;
            }
            if (isEnd && currFileIndex < data.getUcData().getTable().size()){
                final PDFFragmentBean.UcDataBean.TableBean tableBean = data.getUcData().getTable().get(currFileIndex);
                final byte[] file = PDFDownLoad.downLoadPDF2Memory(tableBean.getBrd_url() + URLEncoder.encode(tableBean.getBrd_file(),"utf-8"));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText(tableBean.getBrd_name());
                        initPdfData(file);
                    }
                });
                isEnd = false;//开始展示新文件，将结束标识置为false
                currFileIndex ++;//下一个文件
            }else if (isEnd && currFileIndex == data.getUcData().getTable().size()){
                //如果所有文件轮播完了，回到第一个（暂定）
                isEnd = false;//开始展示新文件，将结束标识置为false
                currFileIndex = 0;//回到第一个文件
                final PDFFragmentBean.UcDataBean.TableBean tableBean = data.getUcData().getTable().get(currFileIndex);
                final byte[] file = PDFDownLoad.downLoadPDF2Memory(tableBean.getBrd_url() + URLEncoder.encode(tableBean.getBrd_file(),"utf-8"));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText(tableBean.getBrd_name());
                        initPdfData(file);
                    }
                });
            }else {
                //否则切换到下一页
                final int currentPage = pdfView.getCurrentPage();
                int pageCount = pdfView.getPageCount();
                int nextPage = -1;
                if (currentPage < pageCount-1){
                    nextPage = currentPage + 1;
                }else {
                    nextPage = 0;
                }
                final int finalNextPage = nextPage;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdfView.jumpTo(finalNextPage,true);
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private float yDistance  = 0;
    private void initPdfData(final byte[] file) {

        pdfView.fromBytes(file)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        if (page == pageCount-1){
                            isEnd = true;
                        }
                    }
                })
                .pageFitPolicy(FitPolicy.HEIGHT)
                .load();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (executors != null){
            executors.shutdown();
        }
    }

    @Override
    public void onLoadDataSucceed(final PDFFragmentBean bean) {
        if (bean.isUtStatus()){
             data = bean;
            /*executors.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            },0,showTime, TimeUnit.SECONDS);*/


        }
    }
}
