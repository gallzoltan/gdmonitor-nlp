package hu.gallz.appservice.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hu.gallz.appservice.model.Decree;

public class DecreeIterator implements Iterator<Decree>{

	private int currentIndex;
    private List<Decree> list;
    
	public DecreeIterator(List<Decree> list) {
		this.list = list;
		this.currentIndex = 0;
	}

	@Override
	public boolean hasNext() {
		while (currentIndex < list.size()) {
			Decree currentDecree = list.get(currentIndex);
			if(isRelevantDecree(currentDecree)) {
				return true;
			}
			currentIndex++;
		}
		return false;
	}

	@Override
	public Decree next() {
		if (!hasNext()) {
            throw new NoSuchElementException();
        }
		return list.get(currentIndex++);
	}
	
	private boolean isRelevantDecree(Decree decree) {
		String input = decree.getDecreeText();
		
		int keyWord1 = input.toLowerCase().indexOf(StringConstants.SEARCH_1, 0);
        int keyWord2 = input.toLowerCase().indexOf(StringConstants.SEARCH_2, 0);
        int keyWord3 = input.toLowerCase().indexOf(StringConstants.SEARCH_3, 0);
        int keyWord4 = input.toLowerCase().indexOf(StringConstants.SEARCH_4, 0);
        
        if(keyWord1 > -1) {
        	return true;
        }
        if(keyWord2 > -1) {
        	return true;
        }
        if(keyWord3 > -1) {
        	return true;
        }
        if(keyWord4 > -1) {
        	return true;
        }
        
		return false;
	}

}
