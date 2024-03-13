public class Vertex {

    public int id;
    public int distance;
    public Vertex parent;

    public Vertex(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return Integer.toString(this.id);
    }

}
