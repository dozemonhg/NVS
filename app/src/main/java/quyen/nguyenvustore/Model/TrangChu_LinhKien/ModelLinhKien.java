package quyen.nguyenvustore.Model.TrangChu_LinhKien;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import quyen.nguyenvustore.Connectinternet.DownloadJSON;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.Model.ObjectClass.ThuongHieu;
import quyen.nguyenvustore.View.TrangChu.TrangChuActivity;

public class ModelLinhKien {
    public List<SanPham> LayDanhSachSanPhamTOP(String tenham, String tenmang){
        List<SanPham> sanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            //Log.d("kiemtra",dataJSON.toString());
            //Log.i("tagconvertstr", "["++"]");
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i<dem; i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));

                sanPhams.add(sanPham);


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhams;
    }

    public List<ThuongHieu> LayDanhSachThuongHieuLon(String tenham,String tenmang){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            //result=getJSONUrl(url);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachThuongHieu.length();

            for (int i = 0; i<dem; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }
}
