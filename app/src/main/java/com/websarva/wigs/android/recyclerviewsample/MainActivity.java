package com.websarva.wigs.android.recyclerviewsample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        //Toolbarを取得。
        Toolbar toolbar = findViewById(R.id.toolbar);
        //ツールバーにロゴを設定。
        toolbar.setLogo(R.mipmap.ic_launcher);
        //アクションバーにツールバーを設定。
        setActionBar(toolbar);
        //CollapsingToolBarLayoutを取得。
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
        //タイトルを設定。
        toolbarLayout.setTitle(getString(R.string.toolbar_title));
        //通常サイズ時の文字色を設定。
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        //縮小時の文字色を設定。
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        //RecyclerViewを取得。
        RecyclerView lvMenu = findViewById(R.id.lvMenu);
        //LinearLayoutManagerオブジェクトを生成。
        LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
        //RecyclerviewにレイアウトマネージャーとしてLinearLayoutManagerを設定。
        lvMenu.setLayoutManager(layout);
        //定食メニューリストデータを生成。
        List<Map<String,Object>> menuList = createTeishokuList();
        //アダプタオブジェクトを生成。
        RecyclerListAdapter adapter = new RecyclerListAdapter(menuList);
        //RecyclerViewにアダプタオブジェクトを設定。
        lvMenu.setAdapter(adapter);
    }

    private List<Map<String,Object>> createTeishokuList(){
        //定食メニューリスト用のListオブジェクトを用意。
        List<Map<String,Object>> menuList = new ArrayList<>();
        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        Map<String,Object> menu = new HashMap<>();
        menu.put("name","から揚げ定食");
        menu.put("price","800");
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);
        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = new HashMap<>();
        menu.put("name","ハンバーグ定食");
        menu.put("price","850");
        menu.put("desc","手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }

    private class RecyclerListViewHolder extends RecyclerView.ViewHolder{
        /**
         * リスト1行分でメニュー名を表示する画面部品。
         */
        public TextView _tvMenuName;
        /**
         * リスト1行分でメニュー金額を表示する画面部品。
         */
        public TextView _tvMenuPrice;

        /**
         * コンストラクタ
         *
         * @param itemView リスト1行分の画面部品。
         */
        public RecyclerListViewHolder(View itemView){
            //親コンストラクタの呼び出し。
            super(itemView);
            //引数で渡されたリスト1行分の画面部品から表示に使われるTextViewを取得。
            _tvMenuName = itemView.findViewById(R.id.tvMenuName);
            _tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice);
        }
    }

    private class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListViewHolder>{
        /**
         * リストデータを保持するフィールド。
         */
        private List<Map<String,Object>> _listData;

        /**
         * コンストラクタ
         *
         * @param listData リストデータ
         */
        public RecyclerListAdapter(List<Map<String,Object>> listData){
            //引数のリストデータをフィールドに格納。
            _listData = listData;
        }

        @Override
        public RecyclerListViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            //レイアウトインフレーターを取得。
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            //row.xmlをインフレートし、1行分の画面部品とする。
            View view = inflater.inflate(R.layout.row,parent,false);
            //ビューホルダーオブジェクトを生成。
            RecyclerListViewHolder holder = new RecyclerListViewHolder(view);
            //生成したビューホルダーをリターン。
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerListViewHolder holder,int position){
            //リストデータから該当1行分のデータを取得。
            Map<String,Object> item = _listData.get(position);
            //メニュー名文字列を取得。
            String menuName = (String) item.get("name");
            //メニュー金額を取得。
            int menuPrice = (Integer) item.get("price");
            //表示用に金額を文字列に変換。
            String menuPriceStr = String.valueOf(menuPrice);
            //メニュー名と金額をビューホルダー中のTextViewに設定。
            holder._tvMenuName.setText(menuName);
            holder._tvMenuPrice.setText(menuPriceStr);
        }

        @Override
        public int getItemCount(){
            //リストデータ中の件数をリターン。
            return _listData.size();
        }
    }
}