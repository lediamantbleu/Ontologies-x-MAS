package ESS;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.ArrayList;

public class AgentESS extends Agent {
	
	private DataStore ds;
	private DFAgentDescription [] receivers;
	private ArrayList<RecordAboutLinks> settings=new ArrayList<RecordAboutLinks>();
	private AID ontologName;
	
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		
		System.out.println("Hello!!! I'm "+this.getLocalName());
		
		
		ServiceDescription sd = new ServiceDescription();
		DFAgentDescription dfd = new DFAgentDescription();
		
		sd.setName("ESS");
		sd.setType("ESS");
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ServiceDescription sd1 = new ServiceDescription();
		DFAgentDescription dfd1 = new DFAgentDescription();
		
		sd1.setName("ESS");
		dfd1.addServices(sd1);
		
		try {
			receivers = DFService.search(this, dfd1);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		ds= new DataStore();
		ds.put("receivers", receivers);
		
		for (int i = 0; i<receivers.length; i++) {
			if (receivers[i].getName().getLocalName().equals("Ontolog")) {
				ontologName = receivers[i].getName();
//				System.out.println(ontologName);
			}
		}
		ds.put("ontolog", ontologName);

		BuildingFilling csp = new BuildingFilling(this.getLocalName());
		settings = csp.createSettings(this.getLocalName());
		ds.put("neighbours", settings);
		
		addBehaviour(new StartofLife(this, ds));
		addBehaviour(new WaitingForRequest(this, ds));
		addBehaviour(new SendingRequestToOntologist(this, ds));
	}
}
