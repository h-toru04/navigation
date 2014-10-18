package jp.co.ly.navigation.entity;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{
    /**
     * serialVersionId
     */
    private static final long serialVersionUID = -8227025050225023283L;
    
    private List<Destination> distinctions;

    public List<Destination> getDistinctions() {
        return distinctions;
    }

    public void setDistinctions(List<Destination> distinctions) {
        this.distinctions = distinctions;
    }
}
