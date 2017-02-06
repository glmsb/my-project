package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abstractAtactivity.BaseActivity;
import com.example.bean.Book;
import com.example.db.MyDB;

public class AddBookActivity extends BaseActivity {
	private Button insert,query;
	private EditText book_name,author,price,pages;
	private MyDB myDB;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		insert=(Button) findViewById(R.id.insert);
		query=(Button) findViewById(R.id.query);
		book_name=(EditText) findViewById(R.id.book_name);
		author=(EditText) findViewById(R.id.author);
		price=(EditText) findViewById(R.id.price);
		pages=(EditText) findViewById(R.id.pages);
		myDB=MyDB.getInstance(AddBookActivity.this);
		
		insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String bookName   = book_name.getText().toString();
				String bookAuthor = author.getText().toString();
				float bookPrice   = Float.parseFloat(price.getText().toString());
				int bookPages     =Integer.parseInt(pages.getText().toString());
				
				//判断输入框不能为空
				if (bookName.equals("")||bookAuthor.equals("")||bookPrice==0 ||bookPages==0) {
					Toast.makeText(getBaseContext(), "信息必须全部填写才能提交", Toast.LENGTH_SHORT).show();
					return;
				}
				else {
					Book book=new Book(bookName, bookAuthor, bookPrice, bookPages);	
					myDB.insert(book);	
					book_name.setText("");
					author.setText("");
					price.setText("");
					pages.setText("");
				}			
			}
		});
		
		
		query.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(AddBookActivity.this,QueryBookActivity.class);
				startActivity(intent);			
			}
			
		});
	}

}
