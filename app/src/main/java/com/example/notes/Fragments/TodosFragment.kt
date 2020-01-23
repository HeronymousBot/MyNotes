package com.example.notes.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.Adapters.PostsAdapter
import com.example.notes.Adapters.TodosAdapter
import com.example.notes.Models.Post
import com.example.notes.Models.Todo
import com.example.notes.R
import com.example.notes.Services.Endpoint
import com.example.notes.Services.RetrofitConfig
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.fragment_todos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosFragment : Fragment() {


    fun newInstance(userId: Int?): TodosFragment {
        val args = Bundle()
        args.putSerializable("userId", userId)
        val fragment = TodosFragment()
        fragment.arguments = args
        return fragment
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_todos, container, false)
        val userId = arguments!!.getSerializable("userId") as Int
        getTodos(userId)



        return view
    }

    fun setComponents(todos: List<Todo>) {
        val todosRecyclerView = recyclerview_Todos
        todosRecyclerView.adapter = TodosAdapter(todos, context!!)
        val layoutManager = LinearLayoutManager(context)
        todosRecyclerView.layoutManager = layoutManager


    }

    fun getTodos(userId:Int?) {
        val retrofitClient = context?.let { RetrofitConfig.getRetrofitInstance(it) }
        val endpoint = retrofitClient!!.create(Endpoint::class.java)
        val callback = endpoint.getTodos(userId)

        callback.enqueue(object : Callback<List<Todo>> {
            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                Toast.makeText(context, "Não conseguimos resgatar os todos.", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                response.body()?.forEach {
                    setComponents(response.body()!!)
                }
            }
        })
    }
}