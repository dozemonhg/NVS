package quyen.nguyenvustore.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.KhuyenMai;
import quyen.nguyenvustore.R;

public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolderKhuyenMai> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMaiList){
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {

        RecyclerView recyclerItemKhuyenMai;
        TextView txtTieuDeKhuyenmai;
        public ViewHolderKhuyenMai(View itemView) {
            super(itemView);

            recyclerItemKhuyenMai = (RecyclerView) itemView.findViewById(R.id.recyclerItemKhuyenMai);
            txtTieuDeKhuyenmai = (TextView) itemView.findViewById(R.id.txtTieuDeKhuyenMai);
        }
    }

    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai,parent,false);

        ViewHolderKhuyenMai viewHolderKhuyenMai = new ViewHolderKhuyenMai(view);
        return viewHolderKhuyenMai;
    }

    @Override
    public void onBindViewHolder(ViewHolderKhuyenMai holder, int position) {
        KhuyenMai khuyenMai = khuyenMaiList.get(position);

        holder.txtTieuDeKhuyenmai.setText(khuyenMai.getTENLOAISP());

        AdapterTopChuotLinhKien adapterTopChuotLinhKien =new AdapterTopChuotLinhKien(context,R.layout.custom_layout_topchuotvabanphim,khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerItemKhuyenMai.setLayoutManager(layoutManager);
        holder.recyclerItemKhuyenMai.setAdapter(adapterTopChuotLinhKien);
    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }


}
