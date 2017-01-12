package parser.Types;

public class Pair4<LL,ML,MR,RR> {
    private LL ll;
    private ML ml;
    private MR mr;
    private RR rr;
    public Pair4(LL ll, ML ml,MR mr, RR rr){
        this.ll = ll;
        this.ml = ml;
        this.mr = mr;
        this.rr = rr;
    }
    public LL getLL(){ return ll; }
    public ML getML(){ return ml; }
    public MR getMR(){ return mr; }
    public RR getRR(){ return rr; }
    public void setLL(LL ll){ this.ll = ll; }
    public void setML(ML ml){ this.ml = ml; }
    public void setMR(MR mr){ this.mr = mr; }
    public void setRR(RR rr){ this.rr = rr; }
}