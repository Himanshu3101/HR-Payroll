package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class SearchEmployeActivity extends AppCompatActivity {
    private RecyclerView ryc_search;
    ArrayList<Searchmodel> employelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employe);

        initialize();
        employelist=new ArrayList<>();
        Searchmodel data=new Searchmodel();
        data.setEmployename("Mohit kumar");
        data.setEmployeemail("abb@gmail.com");
        data.setEmployedesignation("Android Developer");
        employelist.add(data);

        Searchmodel data1=new Searchmodel();
        data1.setEmployename("Himanhsu kumar");
        data1.setEmployeemail("ccccccc@gmail.com");
        data1.setEmployedesignation("Android Developer");

        Searchmodel data2=new Searchmodel();
        data2.setEmployename("Mahindra");
        data2.setEmployeemail("hsjdjasj@gmail.com");
        data2.setEmployedesignation("Dot net Developer");
        employelist.add(data2);

        Searchmodel data3=new Searchmodel();
        data3.setEmployename("Mahindra singh");
        data3.setEmployeemail("mmmmm@gmail.com");
        data3.setEmployedesignation("Dot net Developer");
        employelist.add(data3);

        Searchmodel data4=new Searchmodel();
        data4.setEmployename("Abhishek singh");
        data4.setEmployeemail("hhhh@gmail.com");
        data4.setEmployedesignation("Ui Developer");
        employelist.add(data4);

        Searchmodel data6=new Searchmodel();
        data6.setEmployename("Himanhsu kumar");
        data6.setEmployeemail("ccccccc@gmail.com");
        data6.setEmployedesignation("Android Developer");
        employelist.add(data6);
        Searchmodel data7=new Searchmodel();
        data7.setEmployename("Mahindra");
        data7.setEmployeemail("hsjdjasj@gmail.com");
        data7.setEmployedesignation("Dot net Developer");
        employelist.add(data7);

        Searchmodel data8=new Searchmodel();
        data8.setEmployename("Mahindra singh");
        data8.setEmployeemail("mmmmm@gmail.com");
        data8.setEmployedesignation("Dot net Developer");
        employelist.add(data8);

        Searchmodel data9=new Searchmodel();
        data9.setEmployename("Abhishek singh");
        data9.setEmployeemail("hhhh@gmail.com");
        data9.setEmployedesignation("Ui Developer");
        employelist.add(data9);

        Searchmodel data5=new Searchmodel();
        data5.setEmployename("Amarjeet singh");
        data5.setEmployeemail("snnanqua@gmail.com");
        data5.setEmployedesignation("Ui Developer");
        employelist.add(data5);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this);

        ryc_search.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_search.setItemAnimator(new DefaultItemAnimator());

        SearchAdapter adapter=new SearchAdapter(SearchEmployeActivity.this,employelist);
        ryc_search.setAdapter(adapter);

    }

    public void initialize(){

        ryc_search=findViewById(R.id.ryc_search);
    }
}
