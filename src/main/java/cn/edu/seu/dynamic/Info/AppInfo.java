package cn.edu.seu.dynamic.Info;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "DynamicAPP") public class AppInfo
{

    @Id private String ID;

    private String supportrl;

    private List<String> moreByThisDeveloper;

    private String name;

    private String compatibility;

    private String reviewCount;

    private String language;

    private String category;

    private String developerUrl;

    private String description;

    private Integer ranking;

    private String seller;

    private String link;

    private String lastVersion;

    private String size;

    private String lastUpdate;

    private String icon;

    private List<String> customersAlsoBought;

    private String copyright;

    private String reviewRating;

    private String ageRating;

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getSupportrl()
    {
        return supportrl;
    }

    public void setSupportrl(String supportrl)
    {
        this.supportrl = supportrl;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCompatibility()
    {
        return compatibility;
    }

    public void setCompatibility(String compatibility)
    {
        this.compatibility = compatibility;
    }

    public String getReviewCount()
    {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount)
    {
        this.reviewCount = reviewCount;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getDeveloperUrl()
    {
        return developerUrl;
    }

    public void setDeveloperUrl(String developerUrl)
    {
        this.developerUrl = developerUrl;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getRanking()
    {
        return ranking;
    }

    public void setRanking(Integer ranking)
    {
        this.ranking = ranking;
    }

    public String getSeller()
    {
        return seller;
    }

    public void setSeller(String seller)
    {
        this.seller = seller;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLastVersion()
    {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion)
    {
        this.lastVersion = lastVersion;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public List<String> getMoreByThisDeveloper()
    {
        return moreByThisDeveloper;
    }

    public void setMoreByThisDeveloper(List<String> moreByThisDeveloper)
    {
        this.moreByThisDeveloper = moreByThisDeveloper;
    }

    public List<String> getCustomersAlsoBought()
    {
        return customersAlsoBought;
    }

    public void setCustomersAlsoBought(List<String> customersAlsoBought)
    {
        this.customersAlsoBought = customersAlsoBought;
    }

    public String getCopyright()
    {
        return copyright;
    }

    public void setCopyright(String copyright)
    {
        this.copyright = copyright;
    }

    public String getReviewRating()
    {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating)
    {
        this.reviewRating = reviewRating;
    }

    public String getAgeRating()
    {
        return ageRating;
    }

    public void setAgeRating(String ageRating)
    {
        this.ageRating = ageRating;
    }
}
