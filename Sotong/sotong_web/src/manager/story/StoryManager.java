package manager.story;

import java.util.ArrayList;

import manager.SotongManager;
import manager.home.NeighborHomeManager;
import dao.story.StoryDAO;
import dao.story.StoryViewDAO;
import dao.story.StoryViewVO;

public class StoryManager {
	private StoryDAO storyDAO;
	private StoryViewDAO storyViewDAO;
	
	public StoryManager() {
		this.storyDAO = new StoryDAO();
		this.storyViewDAO = new StoryViewDAO();
	}
	
	public StoryViewVO[] getStoryList(String homeCode) {
		return this.storyViewDAO.getStoryList(homeCode);
	}
	
	public StoryViewVO[] getNeighborStoryList(String homeCode) {
		NeighborHomeManager NHManager = new NeighborHomeManager();
		String[] homeCodeList = NHManager.getAllNeighborHomeCodeList(homeCode);
		
		ArrayList<StoryViewVO> neighborStoryList = new ArrayList<StoryViewVO>();
		
		this.storyViewDAO.getNeighborStoryList(homeCode, neighborStoryList);
		
		System.out.println("������ Ȩ �ڵ� : " + homeCode);
		for (String neighborHomeCode : homeCodeList) {
			System.out.println("����� Ȩ �ڵ� : " + neighborHomeCode);
			this.storyViewDAO.getNeighborStoryList(neighborHomeCode, neighborStoryList);
		}
		
		return neighborStoryList.toArray(new StoryViewVO[neighborStoryList.size()]);
	}
	
	public boolean addStory(String homeCode, String memberCode, String imageName,  
			String contents, String emoticonCode, String storyDate, String scope) {
		
		SotongManager sManager = new SotongManager();
		String sotongContentsCode = sManager.addSotongContents(homeCode, "sc2", contents,  imageName, storyDate, emoticonCode);
		
		return storyDAO.insertStory(homeCode, memberCode, sotongContentsCode, storyDate, scope);
	}
	
	public boolean deleteStory(String homeCode, int storyIndex) {
		SotongManager sManager = new SotongManager();
		String storyCode = this.storyViewDAO.storyIndexCode(homeCode, storyIndex);
	
		if (storyCode == null) {
			System.out.println(" �ڵ带 ã�� ���ߴ�.");
			return false;
		} else {
			System.out.println(storyCode);
		}
		
		String sotongContentsCode = this.storyViewDAO.getSotongContentsCode(storyCode);
		if (sotongContentsCode == null) {
			System.out.println(" ���� �ڵ带 ã�� ���ߴ�.");
			return false;
		} else {
			System.out.println(sotongContentsCode);
		}
		
		int checkDelete = storyDAO.deleteCode(storyCode);
		
		sManager.deleteSotongContents(sotongContentsCode);
		
		System.out.println(checkDelete);
		if (checkDelete != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int deleteStory(String storyCode) {
		SotongManager sManager = new SotongManager();
		String sotongContentsCode = this.storyViewDAO.getSotongContentsCode(storyCode);
		storyDAO.deleteCode(storyCode);
		return sManager.deleteSotongContents(sotongContentsCode);
	}
}
