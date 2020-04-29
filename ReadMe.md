## BottomNavigationView
안드로이드 앱에서 컨텐츠 전환 및 탐색을 위한 Navigation menu는 여러가지 방식으로 구현할 수 있다.

 **1. Drawer Navigation** : 앱 상단의 버튼을 클릭하여 왼쪽 ->오른쪽 슬라이드 형식으로 메뉴가 오픈
 
**2.  App bar 자체**에서앱의 탐색 및 컨텐츠 전환이 가능한 메뉴 모음을 두는  방식 

**BottomNavigationView : 하단의 메뉴를 구성할 때 이용**

![Screenshot_20200429-211749_SOPT_2nd_Semina_Task](https://user-images.githubusercontent.com/56873136/80596441-532adc00-8a61-11ea-870a-87543686fa74.jpg)




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
: 데이터를 한 화면에 스크롤이 가능한 리스트로 표시해주는 위젯

1. 반복될 뷰를 하나 만든다.
2. LayoutManager를 이용하여 데이터들을 배치할 방향을 정해준다.
3. data class를 생성하여 입력할 데이터의 타입을 정해준다.







