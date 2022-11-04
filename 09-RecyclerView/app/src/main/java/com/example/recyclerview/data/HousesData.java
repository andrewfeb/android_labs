package com.example.recyclerview.data;

import com.example.recyclerview.R;
import com.example.recyclerview.models.TraditionalHouse;

import java.util.ArrayList;

public class HousesData {
    private static final String[] houseNames = {
            "Rumoh Aceh",
            "Rumah Bolon",
            "Rumah Limas",
            "Rumah Gadang",
            "Rumah Baloy",
            "Tongkonan",
            "Rumah Honai",
            "Bantayo Poboide",
            "Rumah Kaki Seribu",
            "Rumah Radakng"
    };

    private static final String[] houseProvinces = {
            "Aceh",
            "Sumatera Utara",
            "Sumatera Selatan",
            "Sumatera Barat",
            "Kalimantan Utara",
            "Sulawesi Selatan",
            "Papua Pegunungan",
            "Gorontalo",
            "Papua Barat",
            "Kalimantan Barat"
    };

    private static final int[] houseImages = {
            R.drawable.rumoh_aceh,
            R.drawable.rumah_bolon,
            R.drawable.rumah_limas,
            R.drawable.rumah_gadang,
            R.drawable.rumah_baloy,
            R.drawable.tongkonan_toraja,
            R.drawable.rumah_honai,
            R.drawable.bantayo_poboide,
            R.drawable.rumah_kaki_seribu,
            R.drawable.rumah_radakng
    };

    public static ArrayList<TraditionalHouse> getListData() {
        ArrayList<TraditionalHouse> list = new ArrayList<>();

        for (int i = 0; i < houseNames.length; i++){
            TraditionalHouse traditionalHouse = new TraditionalHouse();
            traditionalHouse.setName(houseNames[i]);
            traditionalHouse.setProvince(houseProvinces[i]);
            traditionalHouse.setImage(houseImages[i]);
            list.add(traditionalHouse);
        }

        return list;
    }
}
