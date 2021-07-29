package com.shabs.tablayoutpageviewfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shabs.tablayoutpageviewfragment.fragments.MovieList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewPageAdapter extends FragmentStateAdapter {

    int pageCount;
    List<Fragment> fragmentList;
    List<String> pageTitle;

    public ViewPageAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public  void setInitialValues(int pageCount,List<Fragment> fragmentList){
        this.pageCount = pageCount;
        this.fragmentList = fragmentList;
    }

    public List<String> getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(List<String> pageTitle) {
        this.pageTitle = pageTitle;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return pageCount;
    }
}
