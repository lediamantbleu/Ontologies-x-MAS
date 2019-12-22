package ESS;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class StartofLife extends OneShotBehaviour {

	private Agent a;
	private DFAgentDescription [] receivers;
	private ArrayList<RecordAboutLinks> neighbours=new ArrayList<RecordAboutLinks>();


	public StartofLife(Agent a, DataStore ds) {
		super(a);
		this.a=a;
		setDataStore(ds);
		receivers= (DFAgentDescription[]) ds.get("receivers");
		neighbours = (ArrayList<RecordAboutLinks>) ds.get("neighbours");
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setProtocol("handshake");

		if (receivers.length!=0) {

			for (int i=0; i<receivers.length; i++) {
				if (!receivers[i].getName().getLocalName().equals(a.getLocalName())) {

					for (int j=0; j<neighbours.size(); j++) {
						if (receivers[i].getName().getLocalName().equals(neighbours.get(j).getNeigbourName())) {
							msg.addReceiver(receivers[i].getName());
//							System.out.println("MESSAGES: " + neighbours.get(j).getNeigbourName());
							msg.setContent(neighbours.get(j).getNeigbourName());
							a.send(msg);							
							System.out.println(a.getLocalName()+": I've added "+receivers[i].getName().getLocalName()+" to list of receivers");
						}
					}
				}
			}
		}
		else {
			System.out.println("nobody");
		}
	}

}
