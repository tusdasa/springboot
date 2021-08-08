package net.tusdasa.demo.utils;

import net.tusdasa.demo.entity.User;

import java.util.LinkedList;

public class DataRepository {

    private volatile static DataRepository dataRepository;

    private LinkedList<User> userArrayList;

    private Integer index = 0;

    private DataRepository(){
        userArrayList = new LinkedList<>();
    }

    public static DataRepository getInstance(){
        if (dataRepository == null){
            synchronized (DataRepository.class){
                if (dataRepository == null){
                    dataRepository = new DataRepository();
                }
            }
        }
        return dataRepository;
    }

    public LinkedList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(LinkedList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
