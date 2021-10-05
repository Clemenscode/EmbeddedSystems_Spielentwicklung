/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 03.11.2015
  * @author Johannes Gundlach 
  */
public class Queue<ContentType> {
  private class QueueNode {
    private ContentType content = null;
    private QueueNode nextNode = null;
    
    /**
    * Ein neues Objekt vom Typ StackNode<ContentType> wird erschaffen. <br />
    * Der Inhalt wird per Parameter gesetzt. Der Verweis ist leer.
    *
    * @param pContent der Inhalt des Knotens
    */
    public QueueNode(ContentType pContent) {
      content = pContent;
      nextNode = null;
    }
    
    /**
    * Der Verweis wird auf das Objekt, das als Parameter uebergeben wird,
    * gesetzt.
    *
    * @param pNext der Nachfolger des Knotens
    */
    public void setNext(QueueNode pNext) {
      nextNode = pNext;
    }
    
    /**
    *
    * @return das Objekt, auf das der aktuelle Verweis zeigt
    */
    public QueueNode getNext() {
      return nextNode;
    }
    
    /**
    * @return das Inhaltsobjekt vom Typ ContentType
    */
    public ContentType getContent() {
      return content;
    }
  }
  
  /* ----------- Ende der privaten inneren Klasse -------------- */
  private QueueNode head;
  private QueueNode tail;
  
  public Queue() {
    head = null;
    tail = null;
  }
  
  public boolean isEmpty() {
    return (head == null);
  }
  
  public void enqueue(ContentType pContent) {
    if (pContent != null) { //checken, ob das uebergebene Objekt nicht gleich null ist.
      
      QueueNode newQnode = new QueueNode(pContent);
      
      if (this.isEmpty()) {
        head = newQnode;
        tail = newQnode;
      } else {
        tail.setNext(newQnode);
        tail = newQnode;
      }
    }
  }
  
  public void dequeu() {
    
    if(!this.isEmpty()){
      head = head.getNext();
    }
    
  }
  
  
  public ContentType front() {
    if (this.isEmpty()) {
      return null;
    } else {
      return head.getContent();
    }
  }
} // end of class qu
