package com.ed.will.dialogtoastsnackbar;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/***
 * Dialog：当提示信息是至关重要的，并且必须要由用户做出决定才能继续的时候，使用Dialog。
 * Toast：当提示信息只是告知用户某个事情发生了，用户不需要对这个事情做出响应的时候，使用Toast。
 * Snackbar：以上两者之外的任何其他场景，Snackbar可能会是你最好的选择。
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dialog_nromal)
    Button dialogNromal;
    @BindView(R.id.toast_normal)
    Button toastNormal;
    @BindView(R.id.toast_fix)
    Button toastFix;
    @BindView(R.id.snackbar_normal)
    Button snackbarNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dialog_nromal, R.id.toast_normal, R.id.toast_fix, R.id.snackbar_normal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_nromal:
                showDialog();
                break;
            case R.id.toast_normal:
                Toast.makeText(this, "Things happened", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toast_fix:
                Util.showToast(this, "Things happened");
                break;
            case R.id.snackbar_normal:
                showSnackBar();
                break;
        }
    }


    /**
     * make()方法的第一个参数需要传入一个view，只要是当前界面布局的任意一个view都可以，
     * Snackbar会使用这个view来自动查找最外层的布局，用于展示Snackbar
     */
    private void showSnackBar() {
        Snackbar.make(snackbarNormal, "data deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title")
                .setMessage("Dialog content.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }


    private static class Util {
        private static Toast toast;

        private static void showToast(Context context, String content) {
            if (toast == null) {
                toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                toast.setText(content);
            }
            toast.show();
        }
    }
}
