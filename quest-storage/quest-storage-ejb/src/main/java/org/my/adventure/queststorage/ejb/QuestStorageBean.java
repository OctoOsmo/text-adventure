package org.my.adventure.queststorage.ejb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateError;
import org.my.adventure.dao_manager.api.dao.QuestDAO;
import org.my.adventure.dao_manager.api.dao.ResourceDAO;
import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by al on 18.02.2016.
 */

@Stateless
public class QuestStorageBean {

    public static final String ALL_GENRES = "Все";

    private final Logger log = LogManager.getLogger(QuestStorageBean.class);

    @EJB
    private QuestDAO questDAO;

    @EJB
    ResourceDAO resourceDAO;

    public List<QuestWrapper> getQuests(){
        return getQuests(ALL_GENRES);
    }

    public byte[] getImageByQuestId(Long questId){
        Quest quest = questDAO.getById(questId);
        Resource resource = null;
        if (quest != null)
            resource = quest.getImage();
        if (resource != null)
            return resource.getData();
        else
            return null;
    }

    public byte[] getImageByResourceId(Long resourceId){
        Resource resource = resourceDAO.getById(resourceId);
        if (resource != null)
            return resource.getData();
        else
            return null;
    }

    public List<QuestWrapper> getQuests(String genre){
        List<Quest> quests = null;
        if (genre.equals(ALL_GENRES))
            quests = questDAO.getAll();
        else
            quests = questDAO.getAllByGenre(genre);
        List<QuestWrapper> questWrappers = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest.getId()!=null){
                QuestWrapper questWrapper = new QuestWrapper(quest);
//            if (questWrapper.getImage() == null)
//                questWrapper.setImage(new DefaultStreamedContent(new ByteArrayInputStream(resourceDAO.getById(1954L).getData()), "image/jpg"));
                if (questWrapper.getRating() == null)
                    questWrapper.setRating(0f);
                questWrappers.add(questWrapper);
            }
        }
        questWrappers.sort(new Comparator<QuestWrapper>() {
            public int compare(QuestWrapper o1, QuestWrapper o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return questWrappers;
    }

    public void deleteQuest(Long id){
        log.debug("deleting " + id+ " " + questDAO.getById(id));
        try {
            questDAO.deleteById(id);
        }catch (HibernateError h){
            log.error(h.getMessage(), h);
        }
    }
}
