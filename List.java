/*
Name: Sergey Gasparyan
Cruz ID: sgaspary
Assignment Name: Programming Assignment 3

public class List {

	private class Node{
		Object data;
		Node prev;
		Node next;
		//Node constructor
		public Node(Object data){
			Node prev = null;
			Node next = null;
			this.data = data;
		}
		public boolean equals(Object x){
			Node n = (Node) x;
			boolean flag = false;
			if(this.data.equals(n)){
				flag = true;
			}
			return flag;
		}
		public String toString(){
			return String.valueOf(data);
		}
	}
	//fields for list
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	private int index;

	// List Constructor
	public List(){
		front = null;
		back = null;
		cursor = null;
		length = 0;
		index = -1;
	} 
	//return length of list
	public int length (){
		return length;
	}
	//returns index of cursor
	public int index(){
		return index;
	}
	//returns the data that the front node is referencing
	public Object front(){
		if(length <= 0){
			throw new RuntimeException(
				"List Error: front() called on empty List");
		} else{
			return front.data;
		}
	}
	//returns the data that the back node is referencing
	public Object back(){
		if(length <= 0){
			throw new RuntimeException(
				"List Error: back() called on empty List");
		} else{
			return back.data;
		}
	}
	//returns the data that the cursor node is referencing 
	public Object get(){
		if(length <= 0 || index < 0){
			throw new RuntimeException(
				"List Error: get() called on empty List");
		}else{
			return cursor.data;
		}
	}
	//Returns true if the list and L are the same 
	//integer sequence, false otherwise.
	public boolean equals(Object x){
		List L = (List) x;
		if (L.length != length){//if lengths are not same return false
			return false;
		}
		boolean flag = true;
		Node first = front;
		Node sec = L.front;
		while(flag && first != null){
			flag = first.data.equals(sec.data);
			first = first.next;
			sec = sec.next;
		}
		return flag;
	}
	//clears the list
	public void clear(){
		front = null;
		back = null;
		cursor = null;
		length = 0;
		index = -1;
	}
	//moves cursor to front of list
	public void moveFront(){
		if(length > 0){
			cursor = front;
			index = 0;
		}
		else{
			throw new RuntimeException(
				"List Error: moveFront() called on empty list");
		}
	}
	//moves cursor to back of list
	public void moveBack(){
		if(length > 0){
			cursor = back;
			index = length-1;
		}else{
			throw new RuntimeException(
				"List Error: moveBack() called on empty list");
		}
	}
	//moves cursor to the left by 1 node
	public void movePrev(){
		if(index != -1){
			if(cursor != front){
				index --;
				cursor = cursor.prev;
			}else {
				index = -1;
				cursor = null;
			}
		}else{
			throw new RuntimeException(
				"List Error: movePrev() called with undefined cursor");
		}
	}
	//moves cursor to the right by 1 node
	public void moveNext(){
		if(index != -1){
			if(cursor != back){
				index++;
				cursor = cursor.next;
			}else{
				index = -1;
				cursor = null;
			}
		}else{
			throw new RuntimeException(
				"List Error: moveNext() called with undefined cursor");
		}
	}
	//adds data to the front of the list
	public void prepend(Object x){
		Node addNode = new Node(x);

		if(length > 0){
			addNode.next = front;
			front.prev = addNode;
			front = addNode;
		}else{
			front = addNode;
			back = addNode;
		}
		length++;
		if(index != -1){
			index++;
		}
	}
	//adds data to the back of the list
	public void append(Object x){
		Node endNode = new Node(x);
		if(length > 0){
			endNode.prev = back;
			back.next = endNode;
			back = endNode;
		}else{
			back = endNode;
			front = endNode;
		}
		length++;
	}
	//inserts data before the node being referenced by the cursor 
	public void insertBefore(Object x){
		if(length > 0 && index >= 0){
			Node newNode = new Node(x);
			if(index == 0){ //if cursor is at front
				newNode.next = cursor;
				cursor.prev = newNode;
				front = newNode;
			}else {
				cursor.prev.next = newNode;
				newNode.prev = cursor.prev;
				newNode.next = cursor;
				cursor.prev = newNode;
			}
			index++;
			length++;
		} else{
			throw new RuntimeException(
				"List Error: insertBefore() called on empty or undefined cursor list.");
		}
	}
	//insert data after the node being referenced by the cursor
	public void insertAfter(Object x){
		if(length > 0 && index >= 0){
			Node newNode = new Node(x);
			if(index == length-1){ //if cursor is at back
				cursor.next = newNode;
				newNode.prev = cursor;
				back = newNode;
			}else{
				cursor.next.prev = newNode;
				newNode.next = cursor.next;
				newNode.prev = cursor;
				cursor.next = newNode;
			}
			length++;
		}else{
			throw new RuntimeException(
				"List Error: insertAfter() called on empty or undefined cursor list.");
		}
	}
	//deletes the front node of the list
	public void deleteFront(){
		if(length > 0){
			Node newFront = front.next;
			front.next = null;
			newFront.prev = null;
			front = newFront;
			length--;
			if(index != -1){
				index--;
				if(index == -1){
					cursor = null;
				}
			}
		}else{
			throw new RuntimeException(
				"List Error: deleteFront() called on empty list.");
		}
	}
	//deletes the back node of the list
	public void deleteBack(){
		if(length > 0){
			if(index == length-1){
				cursor = null;
				index = -1;
			}
			Node newBack = back.prev;
			back.prev = null;
			newBack.next = null;
			back = newBack;
			length--;
		}else{
			throw new RuntimeException(
				"List Error: deleteBack() called on empty list.");
		}
	}
	//deletes the node being referenced by the cursor
	public void delete(){
		if(length > 0 && index >= 0){
			if(front == cursor){
				Node newFront = front.next;
			    front.next = null;
				newFront.prev = null;
				front = newFront;
			}else if(back == cursor){
				Node newBack = back.prev;
				back.prev = null;
				newBack.next = null;
				back = newBack;
			}else{
				cursor.prev.next = cursor.next;
				cursor.next.prev = cursor.prev;
			}

			index = -1;
			length--;
			cursor = null;
		}else{
			throw new RuntimeException(
				"List Error: delete() called on empty or undefined cursor list.");
		}
	}
	public String toString(){
		Node current = front;
		String result = current.toString();
		current = current.next;
		while(current != null){
			result += " " + (String)current.data;
			current = current.next;
		}
		return result;
	}
}
