package Ontologist;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class AgentOntologist extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println(this.getLocalName()+": Hi! I'm Ontologist.");
		
		ServiceDescription sd = new ServiceDescription();
		DFAgentDescription dfd = new DFAgentDescription();
		
		sd.setName("ESS");
		sd.setType("ESS");
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		addBehaviour(new ReceivingRequestFromEss(this));
	}
}
