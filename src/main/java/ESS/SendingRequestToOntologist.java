package ESS;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;

public class SendingRequestToOntologist extends Behaviour {

	private boolean msgarrive;
	private Agent a;
	private ArrayList<RecordAboutLinks> neighbours = new ArrayList<>();
	private DFAgentDescription [] receivers;
	private AID ontologName;

	public SendingRequestToOntologist(Agent a, DataStore ds) {
		super(a);
		// TODO Auto-generated constructor stub
		this.a=a;
		setDataStore(ds);
		neighbours = (ArrayList<RecordAboutLinks>) ds.get("neighbours");
		receivers = (DFAgentDescription[]) ds.get("receivers");
		ontologName= (AID) ds.get("ontologName");
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		MessageTemplate mt = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.INFORM), 
				MessageTemplate.MatchProtocol("EndOfhandshake"));
		ACLMessage inform=a.receive(mt);


		if (inform!=null ) {
			msgarrive=false;

			System.out.println(a.getLocalName()+": "+"I've received Link from "+inform.getSender().getLocalName());
			//			System.out.println(a.getLocalName()+": "+inform.getContent());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
			request.setContent(a.getLocalName() + " ConnectTo " + inform.getSender().getLocalName() + " " + inform.getContent());
			request.setProtocol("Ontolog");
			
			AID receiv= new AID("Ontolog"); 
			for (DFAgentDescription rec: receivers) {
				if (rec.getName().getLocalName().equals("Ontolog"));{
					request.addReceiver(rec.getName());
					receiv=rec.getName();
				}
			}
			a.send(request);
			System.err.println(a.getLocalName()+": I've sent record to Ontolog");

		}
		else {
			block();
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return msgarrive;
	}

}
