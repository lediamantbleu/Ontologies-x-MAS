package ESS;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

public class WaitingForRequest extends Behaviour {

	private Agent a;
	private boolean msgarrive;
	private ArrayList<RecordAboutLinks> neighbours=new ArrayList<RecordAboutLinks>();

	public WaitingForRequest(Agent a, DataStore ds) {
		super(a);
		this.a=a;
		setDataStore(ds);
		neighbours = (ArrayList<RecordAboutLinks>) ds.get("neighbours");
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		MessageTemplate mt = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.REQUEST), 
				MessageTemplate.MatchProtocol("handshake"));
		ACLMessage inform=a.receive(mt);


		if (inform!=null) {
			msgarrive=false;
				if (inform.getContent().equals(a.getLocalName())) {
					
					System.out.println(a.getLocalName()+": "+"I've received handshake from "+inform.getSender().getLocalName());
					//			System.out.println(a.getLocalName()+": "+inform.getContent());
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					double weightOfLink=0;
					for (int i=0; i<neighbours.size(); i++) {
						
						if (neighbours.get(i).getNeigbourName().equals(inform.getSender().getLocalName())) {
							
							weightOfLink = neighbours.get(i).getNeighbourLink();
							System.err.println("WOL: " + weightOfLink);
						}
					}
					ACLMessage request= inform.createReply();
					request.setContent("Link = "+weightOfLink);
					request.setProtocol("EndOfhandshake");
					request.setPerformative(ACLMessage.INFORM);
					a.send(request);
					System.out.println(a.getLocalName()+": I've sent Link to "+inform.getSender().getLocalName());
				}

		}
		else {
			block();
		}
	}





	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
