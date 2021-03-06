package org.my.adventure.queststorage.ejb;

import org.my.adventure.dao_manager.api.entities.Quest;
import org.my.adventure.dao_manager.api.entities.Resource;

/**
 * Created by al on 23.02.2016.
 */
public class QuestWrapper {
    private Long id;
    private String description;
    private String genre;
    private Integer version;
    private Integer ageLimit;
    private Float rating;
    private String name;
    private Resource image;
    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    public QuestWrapper(Quest quest){
        this.id = quest.getId();
        this.description = quest.getDescription();
        this.genre = quest.getGenre();
        this.version = quest.getVersion();
        this.ageLimit = quest.getAgeLimit();
        this.rating = quest.getRating();
        this.name = quest.getName();
        this.image = quest.getImage();
        if (quest.getOwner() != null){
            this.ownerId = quest.getOwner().getId();
        }else{
            this.ownerId = null;
        }
    }
}
