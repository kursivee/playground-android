
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