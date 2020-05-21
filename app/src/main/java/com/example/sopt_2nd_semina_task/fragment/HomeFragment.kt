package com.example.sopt_2nd_semina_task.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_2nd_semina_task.adapter.InstaAdapter
import com.example.sopt_2nd_semina_task.data.InstaData
import com.example.sopt_2nd_semina_task.InstaItemDecoration
import com.example.sopt_2nd_semina_task.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */



class HomeFragment : Fragment() {

    lateinit var instaAdapter: InstaAdapter
    val datas = mutableListOf<InstaData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter =
            InstaAdapter(view.context)
        rv_home.adapter = instaAdapter
        rv_home.addItemDecoration(
            InstaItemDecoration(
                10
            )
        )
        loadDatas()
    }
 fun loadDatas() {
     datas.apply {
         add(
             InstaData(
                 userName = "손흥민",
                 img_profile = "https://img2.yna.co.kr/photo/yna/YH/2019/08/05/PYH2019080501160034000_P4.jpg",
                 img_contents = "https://www.uhakbrain.com/wp-content/uploads/2016/03/Edinburgh-%EC%97%90%EB%94%98%EB%B2%84%EB%9F%AC.jpg"
             )
         )

         add(
             InstaData(
                 userName = "SOPT_Android",
                 img_profile = "https://img2.yna.co.kr/photo/yna/YH/2019/08/05/PYH2019080501160034000_P4.jpg",
                 img_contents = "https://www.raileurope.co.kr/local/cache-gd2/62/80a9f7b267c495ca08391b8c737442.jpg?1580744021"
             )
         )

         add(
             InstaData(
                 userName = "손평화",
                 img_profile = "https://img2.yna.co.kr/photo/yna/YH/2019/08/05/PYH2019080501160034000_P4.jpg",
                 img_contents = "https://www.uhakbrain.com/wp-content/uploads/2016/03/Edinburgh-%EC%97%90%EB%94%98%EB%B2%84%EB%9F%AC.jpg"
             )
         )
     }
     instaAdapter.datas = datas
     instaAdapter.notifyDataSetChanged()

 }
}
