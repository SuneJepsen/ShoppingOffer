package domain;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public class Store {
    private int Id;

    public Store(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
