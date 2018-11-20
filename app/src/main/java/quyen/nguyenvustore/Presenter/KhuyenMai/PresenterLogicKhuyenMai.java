package quyen.nguyenvustore.Presenter.KhuyenMai;

import java.util.List;

import quyen.nguyenvustore.Model.KhuyenMai.ModelKhuyenMai;
import quyen.nguyenvustore.Model.ObjectClass.KhuyenMai;
import quyen.nguyenvustore.View.TrangChu.ViewKhuyenMai;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {

    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSanPhamTheoMaLoai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");
        if(khuyenMaiList.size() > 0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}
