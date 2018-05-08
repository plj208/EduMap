package com.lingkj.android.edumap.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lingkj.android.edumap.R;

/**
 * author: panlijun
 * time: 2018/5/7 上午9:59
 * detail:通用加载进度条效果
 */
public class LoadingFragment extends DialogFragment implements Dialog.OnKeyListener{

    private String message;

    public LoadingFragment(){
        super();
        setStyle(STYLE_NO_FRAME, R.style.LoadingDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.commen_dialog_progress,container,false);
        TextView tvMessage= (TextView) view.findViewById(R.id.tv_progress_message);
        tvMessage.setText(message);
        return super.onCreateView(inflater, container, savedInstanceState);

    }



    public  void setMessage(String msg) {
        this.message=msg;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=new Dialog(getActivity(),getTheme());
        dialog.setOnKeyListener(this);
        return dialog;

    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

        if (event.getKeyCode()== KeyEvent.KEYCODE_BACK){
            getActivity().finish();
            return true;
        }
        return false;
    }
}
