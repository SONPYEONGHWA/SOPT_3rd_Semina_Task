

## **최종 스크린샷 (ItemDecoration 및 ClipToPadding 적용)**


<img src = "https://user-images.githubusercontent.com/56873136/80596441-532adc00-8a61-11ea-870a-87543686fa74.jpg" width = 300dp, height = 600dp>


## BottomNavigationView
안드로이드 앱에서 컨텐츠 전환 및 탐색을 위한 Navigation menu는 여러가지 방식으로 구현할 수 있다.

 **1. Drawer Navigation** : 앱 상단의 버튼을 클릭하여 왼쪽 ->오른쪽 슬라이드 형식으로 메뉴가 오픈
 
**2.  App bar 자체**에서앱의 탐색 및 컨텐츠 전환이 가능한 메뉴 모음을 두는  방식 

**BottomNavigationView : 하단의 메뉴를 구성할 때 이용**



***1. activity_main.xml에 BottomNavigationView와  ViewPager를 추가해준다.***

    <com.google.android.material.bottomnavigation.BottomNavigationView  
      android:id="@+id/main_bottom_navigation"  
      android:layout_width="match_parent"  
      android:layout_height="60dp"  
      android:background="@color/colorPrimary"  
      app:layout_constraintBottom_toBottomOf="parent"  
      app:layout_constraintEnd_toEndOf="parent"  
      app:layout_constraintHorizontal_bias="0.6"  
      app:layout_constraintStart_toStartOf="parent"  
      app:itemIconTint="@color/bottom_selector"  
      app:itemTextColor="@color/bottom_selector"  
      app:menu="@menu/navigation"/>
    <androidx.viewpager.widget.ViewPager  
      android:id="@+id/main_viewpager"  
      android:layout_width="match_parent"  
      android:layout_height="0dp"  
      android:layout_marginTop="1dp"  
      android:layout_marginBottom="1dp"  
      app:layout_constraintBottom_toTopOf="@+id/main_bottom_navigation"  
      app:layout_constraintTop_toBottomOf="@+id/appBarLayout"  
      tools:layout_editor_absoluteX="1dp" />
        
      

 
  
**2. 하단 네비게이션바에 메뉴를 만들어준다.**

 
- resources에 menu타입의 resources directory를 만들어준다

- 만든 menu파일에 navigation.xml을 생성준다.

- 각각의 넣고 싶은 메뉴 아이템들을 만들어준다.


ex) menu.xml

     <menu xmlns:android="http://schemas.android.com/apk/res/android"  
          xmlns:app="http://schemas.android.com/apk/res-auto">  
         <item  android:id="@+id/menu_home"  
          android:icon="@drawable/ic_home_white"  
          android:title="Home"/>  
          
         <item  android:id="@+id/menu_book"  
          android:icon="@drawable/ic_book_white"  
          android:title="Book"/>  
          
         <item  android:id="@+id/menu_person"  
          android:icon="@drawable/ic_person_white"  
          android:title="MyPage"/>  
        </menu>
    
- Vector Asset을 이용하여 메뉴 버튼들을 drawable폴더에 만들어 준다. 
- 버튼이 클릭 되었을 때와 되지 않았을 때의 색깔을 구분하기 위해 resources에 color라는 폴더를 만들어 준다.

    <selector xmlns:android="http://schemas.android.com/apk/res/android">  
      
     <item android:color="@color/white" android:state_checked="true"/>  
     <item android:color="#d9d9d9" android:state_checked="false"/>  
    </selector>

- BottomNavigation에 적용해준다.
  

      app:itemIconTint="@color/bottom_selector"  
      app:itemTextColor="@color/bottom_selector"

## Fragment생성
**1. 각각의 메뉴 버튼들에 대해 Fragment를 생성하여 준다.**

 (HomeFragment, LibraryFragment, MyPageFragment)


**2. Adapter를 생성하여  ViewPager와 Fragment를 연동시켜준다.**

    package com.example.sopt_2nd_semina_task  
      
    import androidx.appcompat.app.AppCompatActivity  
    import android.os.Bundle  
    import android.view.View  
    import androidx.viewpager.widget.ViewPager  
    import com.google.android.material.bottomnavigation.BottomNavigationView  
    import kotlinx.android.synthetic.main.activity_main.*  
      
    class MainActivity : AppCompatActivity() {  
      
        override fun onCreate(savedInstanceState: Bundle?) {  
            super.onCreate(savedInstanceState)  
            setContentView(R.layout.activity_main)  
      
            main_viewpager.adapter = MainPagerAdapter(supportFragmentManager)  
            main_viewpager.offscreenPageLimit = 2  
      
      main_bottom_navigation.setOnNavigationItemSelectedListener {  
      when(it.itemId){  
                    R.id.menu_home -> main_viewpager.currentItem = 0  
      R.id.menu_book -> main_viewpager.currentItem = 1  
      R.id.menu_person -> main_viewpager.currentItem = 2  
      }  
                true  
      }  
      }  
    }

## RecyclerView 생성하기
 **RecyclerView : 데이터를 한 화면에 스크롤이 가능한 리스트로 표시해주는 위젯**

**1. 반복될 뷰를 하나 만든다.**

ex) activity_item_insta.xml

    <androidx.constraintlayout.widget.ConstraintLayout  
      
      xmlns:android="http://schemas.android.com/apk/res/android"  
      
      xmlns:app="http://schemas.android.com/apk/res-auto"  
      
      xmlns:tools="http://schemas.android.com/tools"  
      
      android:layout_width="match_parent"  
      
      android:layout_height="match_parent">  
      
      
      
     <androidx.constraintlayout.widget.ConstraintLayout  
      android:id="@+id/constraintLayout"  
      
      android:layout_width="match_parent"  
      
      android:layout_height="wrap_content"  
      
      android:background="@color/colorPrimary"  
      
      android:paddingHorizontal="24dp"  
      
      android:paddingVertical="8dp"  
      
      app:layout_constraintTop_toTopOf="parent">  
      
      
      
     <de.hdodenhof.circleimageview.CircleImageView  
      android:id="@+id/img_profile"  
      
      android:layout_width="48dp"  
      
      android:layout_height="48dp"  
      
      
      app:layout_constraintStart_toStartOf="parent"  
      
      app:layout_constraintTop_toTopOf="parent" />  
      
      
      
     <TextView  
      android:id="@+id/tv_username"  
      
      android:layout_width="wrap_content"  
      
      android:layout_height="wrap_content"  
      
      android:text="TextView"  
      
      android:textColor="@color/white"  
      
      android:textSize="16sp"  
      
      android:textStyle="bold"  
      
      app:layout_constraintBottom_toBottomOf="@+id/img_profile"  
      
      app:layout_constraintStart_toEndOf="@+id/img_profile"  
      
      app:layout_constraintTop_toTopOf="@+id/img_profile" />  
      
      
      
     <ImageView  
      android:id="@+id/imageView"  
      
      android:layout_width="wrap_content"  
      
      android:layout_height="wrap_content"  
      
      app:layout_constraintBottom_toBottomOf="@+id/tv_username"  
      
      app:layout_constraintEnd_toEndOf="parent"  
      
      app:layout_constraintTop_toTopOf="@+id/tv_username"  
      
      app:srcCompat="@drawable/ic_more" />  
      
     </androidx.constraintlayout.widget.ConstraintLayout>  
      
      
     <ImageView  
      android:id="@+id/img_contents"  
      
      android:layout_width="0dp"  
      
      android:layout_height="0dp"  
      
      android:scaleType="centerCrop"  
      
      app:layout_constraintEnd_toEndOf="parent"  
      
      app:layout_constraintStart_toStartOf="parent"  
      
      app:layout_constraintDimensionRatio="1:1"  
      
      app:layout_constraintTop_toBottomOf="@+id/constraintLayout"  
      
      app:srcCompat="@drawable/ic_launcher_background" />  
      
      
      
    </androidx.constraintlayout.widget.ConstraintLayout>

**2.  HomeFragment에 RecyclerView를 만들고 LayoutManager를 이용하여 데이터들을 배치할 방향을 정해준다.**

    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

**3. data class를 생성하여 입력할 데이터의 타입을 정해준다.**
 

        package com.example.sopt_2nd_semina_task  
        data class InstaData (  
        val userName : String, 
        val img_profile : String,
        val img_contents : String )

##  ViewHolder를 만들어준다.

    
   - ViewHolder는 데이터를 뷰의 어느 위치에 넣을지를 정의한다.

    package com.example.sopt_2nd_semina_task  
    import android.view.View 
    import android.widget.ImageView 
    import android.widget.TextView  
    import androidx.recyclerview.widget.RecyclerView  
    import com.bumptech.glide.Glide  
    class InstaViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {  
    val tv_username = itemView.findViewById<TextView>(R.id.tv_username)  
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)  
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)  
  
    fun bind (instaData : InstaData){  
        tv_username.text = instaData.userName  
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)  
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)  
        }  
    }

##  InstaAdapter 생성
**: 실질적으로 데이터들을 뷰에 띄워주기 위한 Adapter**



        package com.example.sopt_2nd_semina_task  
        import android.content.Context  
        import android.text.Layout  
        import android.view.LayoutInflater  
        import android.view.ViewGroup  
        import androidx.recyclerview.widget.RecyclerView  
        
        class InstaAdapter (private val context : Context) : RecyclerView.Adapter<InstaViewHolder>() {  
        
           var datas = mutableListOf<InstaData>() 
         
           override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder { 
         
           val view = LayoutInflater.from(context).inflate(R.layout.activity_item_insta,parent,false)  
        
           return InstaViewHolder(view)  
                } 
                
           override fun getItemCount(): Int {  
           return datas.size 
                } 
                
           override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {  
           holder.bind(datas[position]) 
              }  
        }
- onCreateViewHolder :  각 아이템을 위한 XML 레이아웃을 이용해 뷰 객체를 만든 후 뷰홀더에 담아 리턴한다.
이때 XML 레이아웃을 인플레이션하여 설정할 ViewGroup 객체는 onCreateViewHolder 메소드의 파라미터로 전달된다.

- onBindViewHolder : 객체를 전달받아 뷰홀더 안에 있는 뷰에 데이터를 설정하는 역할을 한다.  
- getItemCount() : data의 갯수를 반환해준다.

## RecyclerView에 Adapter 적용하기

    package com.example.sopt_2nd_semina_task  
    import android.graphics.Canvas  
    import android.graphics.Color  
    import android.graphics.Rect  
    import android.os.Bundle  
    import androidx.fragment.app.Fragment  
    import android.view.LayoutInflater  
    import android.view.View  
    import android.view.ViewGroup  
    import androidx.recyclerview.widget.RecyclerView  
    import kotlinx.android.synthetic.main.fragment_home.*  
    /**  
     * A simple [Fragment] subclass. */  
    
     * class HomeFragment : Fragment() {  
  
    lateinit var instaAdapter: InstaAdapter  
    val datas = mutableListOf<InstaData>()  
  
    override fun onCreateView(  
        inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle?  
    ): View? {  
        // Inflate the layout for this fragment  
        return inflater.inflate(R.layout.fragment_home, container, false)  
    }  
  
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  
        super.onViewCreated(view, savedInstanceState)  
        instaAdapter = InstaAdapter(view.context)  
        rv_home.adapter = instaAdapter   
        loadDatas()  
    }   
    fun loadDatas() {  
     datas.apply {  
    add(  
             InstaData(  
                 userName = "손흥민",  
    img_profile ="https://img2.yna.co.kr/photo/yna/YH/2019/08/05/PYH2019080501160034000_P4.jpg",  
    img_contents = "https://www.uhakbrain.com/wp-content/uploads/2016/03/Edinburgh-%EC%97%90%EB%94%98%EB%B2%84%EB%9F%AC.jpg"  
    ))  
  
         add(  
             InstaData(  
                 userName = "SOPT_Android",  
    img_profile = "https://img2.yna.co.kr/photo/yna/YH/2019/08/05/PYH2019080501160034000_P4.jpg",  
    img_contents = "https://www.raileurope.co.kr/local/cache-gd2/62/80a9f7b267c495ca08391b8c737442.jpg?1580744021"  
    ))  
  
         add(  
             InstaData(  
                 userName = "손평화",  
    img_profile = "https://img2.yna.co.kr/photo/yna/YH/2019/08/05/PYH2019080501160034000_P4.jpg",  
    img_contents = "https://www.uhakbrain.com/wp-content/uploads/2016/03/Edinburgh-%EC%97%90%EB%94%98%EB%B2%84%EB%9F%AC.jpg"  
    ))  
     }  
    instaAdapter.datas = datas  
    instaAdapter.notifyDataSetChanged()  
     rv_home.addItemDecoration(InstaItemDecoration(10))  
       }  
    }

## RecyclerView의 ItemDecoration


- RecyclerView에 구분선을 주기 위해 ItemDecoration을 사용한다.

**1. InstaItemDecoration 클래스를 생성해준다.**

    package com.example.sopt_2nd_semina_task  
  
    import android.graphics.Rect  
    import android.view.View  
    import androidx.recyclerview.widget.RecyclerView  
  
    class InstaItemDecoration( private val divHeight:Int) : RecyclerView.ItemDecoration(){  
  
    override fun getItemOffsets(  
        outRect: Rect,  
    view: View,  
    parent: RecyclerView,  
    state: RecyclerView.State  
    ) {  
        super.getItemOffsets(outRect, view, parent, state)  
        outRect.left = divHeight  
    outRect.right = divHeight  
    outRect.top = divHeight  
    outRect.bottom = divHeight  
      }   
    }
