package com.mvpdemo.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mvpdemo.R;
import com.mvpdemo.mvc.bean.User;
import com.mvpdemo.mvc.presenter.UserLoginPresenter;
import com.mvpdemo.mvc.view.IUserLoginView;
import com.umeng.message.PushAgent;

import static anet.channel.util.Utils.context;

/**
 * 作者：flyframe on 2017/4/12 15:53
 * 邮箱：jxsm6@163.com
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IUserLoginView{

    private ProgressBar progressBar;
    private EditText userName,pwd;
    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        initView();
        PushAgent.getInstance(context).onAppStart();
    }

    private void initView(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        userName = (EditText) findViewById(R.id.editText);
        pwd = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                /**登录*/
                userLoginPresenter.login();
                break;
            case R.id.button2:
                /**清除*/
                userLoginPresenter.clear();
                break;
        }
    }

    @Override
    public String getUserName() {
        if (userName == null)
            return null;
        return userName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        if (pwd == null)
            return null;
        return pwd.getText().toString().trim();
    }

    @Override
    public void clearUserName() {
        userName.setText("");
    }

    @Override
    public void clearPassword() {
        pwd.setText("");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }
}
