package it.progmob.gradetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.progmob.gradetracker.databinding.ItemSubjectsBinding


class GradeAdapter(var mContext: Context, var subjectList: List<Subject>) : RecyclerView.Adapter<GradeAdapter.SubjectHolder>() {

    inner class SubjectHolder(val view: ItemSubjectsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val binding = ItemSubjectsBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return SubjectHolder(binding)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        val subject = subjectList.get(position)
        val view = holder.view
        view.subjectItem.text = subject.name
        view.gradeItem.text = subject.grade.toString()
        view.creditItem.text = subject.credits.toString()
    }

}