package com.wzc.masterblogdemo.annotation.compile;

import android.support.v7.app.AppCompatActivity;
/**
 * Android 如何编写基于编译时注解的项目
 * https://blog.csdn.net/lmj623565791/article/details/51931859
 *
 * @author wzc
 * @date 2018/7/30
 */
public class CompileAnnotationActivity extends AppCompatActivity {

//    @Bind(R.id.textView2)
//    TextView mTextView;
//
//    @Bind(R.id.button2)
//    Button mButton;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_compile_annotation);
//        ViewInjector.injectView(this);
//
//        mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CompileAnnotationActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CompileAnnotationActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fl, new CompileAnnotationFragment())
//                    .commit();
//        }
//    }
//
//    public static class CompileAnnotationFragment extends Fragment {
//        @Bind(R.id.textView3)
//        TextView mTextView;
//        @Override
//        public void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//        }
//
//        @Nullable
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            return inflater.inflate(R.layout.fragment_compile_annotation, container, false);
//        }
//
//        @Override
//        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//            super.onViewCreated(view, savedInstanceState);
//            ViewInjector.injectView(this, view);
//            mTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
}
