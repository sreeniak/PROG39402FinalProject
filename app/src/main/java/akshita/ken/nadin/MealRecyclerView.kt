package akshita.ken.nadin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealRecyclerView (private val mealList:List<ListMeal>): RecyclerView.Adapter<MealRecyclerView.MyViewHolder>() {
    class MyViewHolder(mealView: View): RecyclerView.ViewHolder(mealView){

        val mealDate: TextView = mealView.findViewById(R.id.mealDateText)
        val mealName: TextView = mealView.findViewById(R.id.mealNameText)
        val mealType: TextView = mealView.findViewById(R.id.mealTypeText)
        val mealCalories: TextView = mealView.findViewById(R.id.mealCaloriesText)
      
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealRecyclerView.MyViewHolder {
        val mealView = LayoutInflater.from(parent.context).inflate(R.layout.listmeal, parent, false)
        return MyViewHolder(mealView)
    }

    override fun onBindViewHolder(holder: MealRecyclerView.MyViewHolder, position: Int) {
        val currentMeal = mealList[position]
        holder.mealDate.text = currentMeal.text1
        holder.mealName.text = currentMeal.text2
        holder.mealType.text = currentMeal.text3
        holder.mealCalories.text = currentMeal.text4.toString()
    }

    override fun getItemCount() = mealList.size
}