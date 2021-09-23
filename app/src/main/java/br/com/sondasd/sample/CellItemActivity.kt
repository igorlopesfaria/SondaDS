package br.com.sondasd.sample

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sondasd.R
import br.com.sondasd.cell.SondaCellItemView
import br.com.sondasd.cell.StateView
import br.com.sondasd.databinding.ActivitySampleCellBinding

class CellItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleCellBinding

    private var adapter: CellItemAdapter = CellItemAdapter()
    private var adapterSkeleton: CellItemAdapter = CellItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleCellBinding.inflate(layoutInflater)
        setAdapter()
        setContentView(binding.root)
    }

    private fun setAdapter() {
        binding.cellsList.layoutManager = LinearLayoutManager(this)
        binding.cellsList.adapter = adapter
        adapter.populateList(cellItems)

        binding.cellsListSkeleton.layoutManager = LinearLayoutManager(this)
        binding.cellsListSkeleton.adapter = adapterSkeleton
        adapterSkeleton.populateList(cellItemsEmpty)
    }

}

class CellItemAdapter: RecyclerView.Adapter<CellViewHolder>() {
    private lateinit var itemList: List<CellItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        return CellViewHolder(SondaCellItemView(parent.context))
    }

    fun populateList(item: List<CellItem>) {
        this.itemList = item
    }
    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(itemList[position], position)
    }
}
class CellItem(val id: Int? = null, val title: String? = "", val subtitle: String? = "", @DrawableRes val icon: Int = 0, val selected:Boolean? = false)

val cellItems = arrayListOf(
    CellItem(1, title = "Single Text 1", selected = true),
    CellItem(2, "Single Text 2"),
    CellItem(3, "Single Text with icon 3", icon = R.drawable.ic_like),
    CellItem(4, "Single Text with icon 4", icon = R.drawable.ic_like),
    CellItem(5, "Title 5", "Subtitle 5"),
    CellItem(6, "Title 6", "Subtitle 6"),
    CellItem(7, "Title 7", "Subtitle 7", icon = R.drawable.ic_like),
    CellItem(8,"Title 8", "Subtitle 8", icon = R.drawable.ic_like)
)

val cellItemsEmpty = arrayListOf(
    CellItem(),
    CellItem(),
    CellItem(),
    CellItem(),
    CellItem(),
    CellItem(),
    CellItem(),
    CellItem()
)

class CellViewHolder(private val cellItemView: SondaCellItemView) : RecyclerView.ViewHolder(cellItemView) {
    fun bind(cellItem: CellItem, position: Int) {
        cellItemView.apply {
            cellItem.id?.let {
                currentState = StateView.State.NORMAL
                cellItem.title?.let { title = it }
                cellItem.subtitle?.let { subtitle = it }
                cellItem.icon.let { iconResource = it }
                if (cellItem.selected != null && cellItem.selected)
                    iconIndicatorResource = R.drawable.ic_done
            } ?: run {
                currentState = StateView.State.SKELETON
                cellItem.title?.let { title = it }
                lineTint = ContextCompat.getColor(context, br.com.sondasd.cell.R.color.sonda_neutral_dark)
                if(position%2 ==0){
                    cellItem.subtitle?.let { subtitle = it }
                    cellItem.icon.let { iconResource = it }
                }
            }
        }
    }
}

