package tw.org.iii.appps.p_navigation_toolbar;
//1.加入api
//def nav_version = "2.2.0"
//        // Java language implementation
//        implementation "androidx.navigation:navigation-fragment:$nav_version"
//        implementation "androidx.navigation:navigation-ui:$nav_version"

//2.在Res/Navigation資源區底下創建Navigation
//3.在Navigation/graph 創建圖形流程區
//4.按下add/選擇一個Frgament/Activity加入當起始頁面
//5.我們希望是一個Activity多個Fragment,所以在起始頁面內加入Fragament
//6.將Fragment設定為可以加入後
//7.AFragment為主頁
//8.這頁可以控制所有的toolbar

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private Toolbar toolbar;

    //當被選到item時
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:  //這個是返回箭頭的id,代表當選到home圖案時
                navController.navigateUp(); //回到上一頁
                Log.v("hank","onOptionsItemSelected : + menuItem.getItemId:" + item.getItemId());
                return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.取得toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar(); //這邊為了要設定返回建

        navController = Navigation.findNavController(MainActivity.this,R.id.main_fragment);
        //當目的地頁面跳轉時
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.BFragment){ //跳轉頁面id == 頁面B的id
//                    toolbar.setVisibility(View.GONE); //toolbar隱藏

                    toolbar.setTitle("這是b2頁面"); //設定標題
//                    toolbar.setBackgroundColor(Color.BLUE); //設定背景顏色
                    actionBar.setDisplayHomeAsUpEnabled(true); //設定返回鍵按鈕


                    Log.v("hank","onDestinationChanged =>  destination.getId:" + destination.getId() +"BFragment" + R.id.BFragment);
                }
            }
        });


    }
}
