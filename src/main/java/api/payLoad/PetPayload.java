package api.payLoad;

import java.util.List;

public class PetPayload {

	
	    private int id;
	    private Category category;
	    private String name;
	    private List<String> photoUrls;
	    private List<Tag> tags;
	    private String status;
	    

	 
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<String> getPhotoUrls() {
	        return photoUrls;
	    }

	    public void setPhotoUrls(List<String> photoUrls) {
	        this.photoUrls = photoUrls;
	    }

	    public List<Tag> getTags() {
	        return tags;
	    }

	    public void setTags(List<Tag> tags) {
	        this.tags = tags;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public static class Category {
	        private long id;
	        private String Storename;

	        // Constructors, getters, and setters...

	        public long getId() {
	            return id;
	        }

	        public void setId(long id) {
	            this.id = id;
	        }

	        public String getStoreName() {
	            return Storename;
	        }

	        public void setStoreName(String Storename) {
	            this.Storename = Storename;
	        }
	    }

	    public static class Tag {
	        private long id;
	        private String name;

	        // Constructors, getters, and setters...

	        public long getId() {
	            return id;
	        }

	        public void setId(long id) {
	            this.id = id;
	        }

	        public String getName() {
	            return name;
	        }

	        public void setName(String name) {
	            this.name = name;
	        }
	    }
	}

	
	
