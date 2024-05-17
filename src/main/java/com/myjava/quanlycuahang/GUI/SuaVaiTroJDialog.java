package com.myjava.quanlycuahang.GUI;

import java.util.HashMap;

import com.myjava.quanlycuahang.BUS.ChucNangBUS;
import com.myjava.quanlycuahang.DTO.ChucNangDTO;
import com.myjava.quanlycuahang.DTO.VaiTroDTO;

public class SuaVaiTroJDialog extends ThemVaiTroJDialog{
    private VaiTroDTO vt;

    public SuaVaiTroJDialog(java.awt.Frame parent, boolean modal, VaiTroDTO vt) {
        super(parent, modal);
        this.vt = vt;
        // setPermissionTable();
    }
    // public static void main(String[] args) {
    //     VaiTroDTO vt = new VaiTroDTO(1, "administrator", new HashMap<>());
    //     new SuaVaiTroJDialog(null, true, vt).setVisible(true);
    // }

    // public void setPermissionTable() {
    //     super.tbModelQuyen.setRowCount(0);
    //     for(ChucNangDTO cn : ChucNangBUS.getInstance().getList()) {
    //         for (String key : vt.getListQuyen().keySet()) {
    //             if (key.equals(cn.getMaChucNang())) {
    //                 super.tbModelQuyen.addRow(new Object[] {cn.getTenChucNang(), vt.getListQuyen().get(key).get(0), vt.getListQuyen().get(key).get(1), vt.getListQuyen().get(key).get(2)});
    //             }
    //         }
    //     }
    // }
}
