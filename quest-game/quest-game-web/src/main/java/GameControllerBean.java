import org.my.adventure.questgame.ejb.GameBean;
import org.my.adventure.questgame.impl.wrappers.NodeWrapper;
import org.my.adventure.questgame.impl.wrappers.TransitionWrapper;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.List;


/**
 * Created by ������ on 09.02.2016.
 */

@ManagedBean(name="gameController")
@ViewScoped
public class GameControllerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private long questId;
    private NodeWrapper node;
    private boolean soundEnabled=false;

    @EJB
    GameBean gameBean;

    public void loadQuest() {
        node = gameBean.loadGame(questId);
    }

    public void changeCurrentNode(long transId){
        node = gameBean.getNextWrappedNode(questId,transId);
    }

    public void refresh(){
        gameBean.refresh(questId);
        node = gameBean.loadGame(questId);
    }

    public void addMessage() {
        String summary = soundEnabled ? "Sound ON" : "Sound OFF";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public String getName() {
        return gameBean.getWrappedQuest(questId).getQuestName();
    }

    public String getNodeText(){
        return node.getText();
    }

    public String getNodeName(){
        return node.getName();
    }

    public List<TransitionWrapper> getCurrentTransitions() {
        return  node.getTransitions();
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }

    public long getQuestId(){
        return questId;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    public long getImageResourceId(){
        return node.getImageResourceId();
    }

    public long getSoundResourceId(){
        return node.getSoundResourceId();
    }

}
