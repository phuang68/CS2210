public class TTTDictionary implements TTTDictionaryADT {
	private LinkedList[] Dict;// create a dictionary
	private LinearNode node;// create a node to store TTTRecord
	private LinearNode<TTTRecord> current;// node used in method get
	private String[] search;// a string array to store the inserted configuration and will be used to check
							// duplicated string
	private int count;// an integer to count the number of inserted elements
	private int j;// an integer to store the hash function
	private int size;// an integer to store the size of the dictionary

	//
	public TTTDictionary(int size) {
		this.Dict = new LinkedList[size];
		this.search = new String[size];
		this.count = 0;
		this.size = size;
		this.node = new LinearNode();
		for (int i = 0; i < size; i++) {// store an empty linked list in every entry of the dictionary
			this.Dict[i] = new LinkedList();
		}
	}

	// hash function will return the hash index of the configuration
	private int hash(String config) {
		int hash = config.charAt(0);
		for (int i = 0; i < config.length(); i++) {
			hash = (hash * 571 + config.charAt(i)) % Dict.length;// choose a prime number to do the hashing
		}
		return hash;
	}

	// This method adds the config string to the search array
	private void addToSearch(String config) {
		if (count == search.length)
			expandCapacity();// if the search array reaches to its capacity,expand the capacity twice
		search[count] = config;// add the config string to the search array
		count++;// increment the count
	}

	// This methods inserts the given TTTRecord record in the dictionary.
	public int put(TTTRecord record) throws DuplicatedKeyException {
		j = hash(record.getConfiguration());// obtain the config's
		node.setElement(record);
		if (Dict[j].isEmpty()) {
			Dict[j].insertLast(node);
			addToSearch(record.getConfiguration());
			return 0;
		} else {
			for (int i = 0; i < search.length; i++)
				if (search[i] == record.getConfiguration())
					throw new DuplicatedKeyException(record.getConfiguration());
			Dict[j].insertLast(node);
			return 1;
		}
	}

	// This method will expand the capacity of the search array
	private void expandCapacity() {
		String[] largerSearch = new String[search.length];
		for (int i = 0; i < search.length; i++)
			largerSearch[i] = search[i];
		search = largerSearch;
	}

	// This method removes the record with the given key config from the dictionary.
	public void remove(String config) throws InexistentKeyException {
		j = hash(config);
		current = Dict[j].head;
		if (Dict[j].isEmpty()) // throw exception if the config is not in the dictionary
			throw new InexistentKeyException(config);
		if (current.getElement().getConfiguration().equals(config)) {// check the first node see if it's the node that
																		// stored the config
			Dict[j].remove(current.getElement());// remove the node that stored the target config
			count--;
		}
		while ((current.getNext() != null)) {// it will traverse all the node in the linked list with specific hash
												// integer in the dictionary
			if (current.getElement().getConfiguration().equals(config)
					&& !current.getNext().getElement().equals(null)) {
				Dict[j].remove(current.getElement());// remove the node that stored the target config
			}
			current = current.getNext();
		}
		if (current.getElement() == null && current.getNext() == null)
			throw new InexistentKeyException(config);
	}

	// The get() method takes a configuration as an input
	// and returns the TTTrecord if it exists in the TTTDictionary
	public TTTRecord get(String config) {
		j = hash(config);
		current = Dict[j].head;
		if (current == null || current.getElement() == null)// return null if the config is not in the dictionary
			return null;
		if (current.getElement().getConfiguration().equals(config)) {// return the element when the config is in the
																		// first node of the dictionary
			return current.getElement();
		} else {
			while (current.getNext() != null) {// it will traverse all the node in the linked list with specific hash
												// integer in the dictionary
				if (!current.getNext().getElement().equals(null)
						&& !current.getElement().getConfiguration().equals(config)) {
					current = current.getNext();
				} else
					return current.getElement();
			}
		}
		return current.getElement();
	}

	// This method returns the number of TTTRecord objects stored in the dictionary.
	public int numElements() {
		return count;
	}

}
