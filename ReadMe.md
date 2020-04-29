## BottomNavigationView
안드로이드 앱에서 컨텐츠 전환 및 탐색을 위한 Navigation menu는 여러가지 방식으로 구현할 수 있다.
 **1. Drawer Navigation** : 앱 상단의 버튼을 클릭하여 왼쪽 ->오른쪽 슬라이드 형식으로 메뉴가 오픈
**2.  App bar 자체**에서앱의 탐색 및 컨텐츠 전환이 가능한 메뉴 모음을 두는  방식 
**BottomNavigationView : 하단의 메뉴를 구성할 때 이용**

![Screenshot_20200429-211749_SOPT_2nd_Semina_Task](https://user-images.githubusercontent.com/56873136/80596441-532adc00-8a61-11ea-870a-87543686fa74.jpg)




***먼저 activity_main.xml에 BottomNavigationView를 추가해준다.***

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
    
   
 **하단 네비게이션바에 메뉴를 만들어준다.** 
1. resources에 menu타입의 resources directory를 만들어준다.
2. 만든 menu파일에 navigation.xml을 생성준다.
3. 각각의 넣고 싶은 메뉴 아이템들을 만들어준다.


- menu.xml

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
    
4. Vector Asset을 이용하여 메뉴 버튼들을 drawable폴더에 만들어 준다. 
5. 버튼이 클릭 되었을 때와 되지 않았을 때의 색깔을 구분하기 위해 resources에 color라는 폴더를 만들어 준다.
<selector xmlns:android="http://schemas.android.com/apk/res/android">  
  
    <selector xmlns:android="http://schemas.android.com/apk/res/android">  
      
     <item android:color="@color/white" android:state_checked="true"/>  
     <item android:color="#d9d9d9" android:state_checked="false"/>  
    </selector>





