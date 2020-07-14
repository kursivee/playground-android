
# Recycler View Playground  
Playground for messing with recycler view implementations  
  
Release page  
https://developer.android.com/jetpack/androidx/releases/recyclerview  
  
## Items  
### Concat Adapter  
[What is ConcatAdapter](https://medium.com/androiddevelopers/merge-adapters-sequentially-with-mergeadapter-294d2942127a)  
[Android Doc](https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter)
  
#### Notes  
- Curious how this is going to work with list of lists. Might not be for this use case.  
	 - [Header]  
	 - [(n * [Adapter 1, Adapter 2, Adapter 1])]  
	 - [Footer]
- Used to be called MergeAdapter but was renamed

### List Adapter
[What is List Adapater](https://medium.com/simform-engineering/listadapter-a-recyclerview-adapter-extension-5359d13bd879)
[ListAdapter Doc](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
[DiffUtil Doc](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)

#### Notes
- Basically a convenience class that's backed by diffutil for more performant lists.
- `notifyDataSetChanged` isn't performant since it recreates everything
- `DiffUtil` allows user to define equality so that recycler view knows which content actually changed