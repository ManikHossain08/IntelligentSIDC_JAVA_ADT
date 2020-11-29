package ADT;

//import java.util.NoSuchElementException;

class LinkedHashEntry {
	private long key;
	private long value;
	private LinkedHashEntry next;

	LinkedHashEntry(long key, long value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public long getKey() {
		return key;
	}

	public LinkedHashEntry getNext() {
		return next;
	}

	public void setNext(LinkedHashEntry next) {
		this.next = next;
	}
}
