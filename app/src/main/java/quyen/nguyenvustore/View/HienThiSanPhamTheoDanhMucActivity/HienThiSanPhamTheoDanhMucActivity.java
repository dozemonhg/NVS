package quyen.nguyenvustore.View.HienThiSanPhamTheoDanhMucActivity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

//import android.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import quyen.nguyenvustore.Adapter.AdapterTopChuotLinhKien;
import quyen.nguyenvustore.Model.ObjectClass.ILoadMore;
import quyen.nguyenvustore.Model.ObjectClass.LoadMoreScroll;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import quyen.nguyenvustore.R;
import quyen.nguyenvustore.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

public class HienThiSanPhamTheoDanhMucActivity extends Fragment implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener,ILoadMore {

    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;
    ProgressBar progressBar;
    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    AdapterTopChuotLinhKien adapterTopChuotLinhKien;

    int masp;
    boolean kiemtra;
    Toolbar toolbar;
    List<SanPham> sanPhamList1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthisanphamtheodanhmuc,container,false);

        setHasOptionsMenu(false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = (Button) view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAI",0);
        String tensanpham = bundle.getString("TENLOAI");
        kiemtra = bundle.getBoolean("KIEMTRA",false);

        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);

        btnThayDoiTrangThaiRecycler.setOnClickListener(this);

        toolbar.setTitle(tensanpham);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menutrangchu,menu);
    }


    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;
        if (danggrid) {
            layoutManager = new GridLayoutManager(getContext(), 2);
            adapterTopChuotLinhKien = new AdapterTopChuotLinhKien(getContext(), R.layout.custom_layout_topchuotvabanphim, sanPhamList1);

        }else {
            layoutManager = new LinearLayoutManager(getContext());
            adapterTopChuotLinhKien = new AdapterTopChuotLinhKien(getContext(), R.layout.custom_layout_list_topchuotvabanphim, sanPhamList1);

        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopChuotLinhKien);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopChuotLinhKien.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThayDoiTrangThaiRecycler:
                danggrid = !danggrid;
                sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);
                ;break;
        }
    }

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> listsanPhamsLoadMore = sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(masp,kiemtra,tongitem,progressBar);
        sanPhamList1.addAll(listsanPhamsLoadMore);
        adapterTopChuotLinhKien.notifyDataSetChanged();

    }
}

