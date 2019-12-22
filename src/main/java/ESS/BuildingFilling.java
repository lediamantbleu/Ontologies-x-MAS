package ESS;

import java.util.ArrayList;

public class BuildingFilling {
	
	private String agentName;

	public BuildingFilling(String agentName) {
		this.agentName = agentName;
	}
	
	public ArrayList<RecordAboutLinks> createSettings (String agentName){
		
		ArrayList<RecordAboutLinks> result=new ArrayList<RecordAboutLinks>();
		
		if (agentName.equals("ESS1")) {
			/**
			 * Neighbours
			 */
			RecordAboutLinks neighbour1=new RecordAboutLinks();
			neighbour1.setNeigbourName("ESS2");
			neighbour1.setNeighbourLink(60.0);
			result.add(neighbour1);
			
			RecordAboutLinks neighbour2=new RecordAboutLinks();
			neighbour2.setNeigbourName("ESS3");
			neighbour2.setNeighbourLink(120.0);
			result.add(neighbour2);
			
			RecordAboutLinks neighbour3=new RecordAboutLinks();
			neighbour3.setNeigbourName("ESS4");
			neighbour3.setNeighbourLink(38.0);
			result.add(neighbour3);

			RecordAboutLinks neighbour4=new RecordAboutLinks();
			neighbour4.setNeigbourName("ESS5");
			neighbour4.setNeighbourLink(85.0);
			result.add(neighbour4);
			/**
			 * Generators
			 */
			RecordAboutLinks generator1=new RecordAboutLinks();
			generator1.setGeneratorName("Wind");
			/**
			 * Number of consumers
			 */
			RecordAboutLinks consumers=new RecordAboutLinks();
			consumers.setNumberOfConsumers(3);
		}
		
		if (agentName.equals("ESS2")) {
			RecordAboutLinks neighbour1=new RecordAboutLinks();
			neighbour1.setNeigbourName("ESS1");
			neighbour1.setNeighbourLink(60.0);
			result.add(neighbour1);
			
			RecordAboutLinks neighbour2=new RecordAboutLinks();
			neighbour2.setNeigbourName("ESS3");
			neighbour2.setNeighbourLink(80.0);
			result.add(neighbour2);

			RecordAboutLinks neighbour3=new RecordAboutLinks();
			neighbour3.setNeigbourName("ESS4");
			neighbour3.setNeighbourLink(38.0);
			result.add(neighbour3);

			RecordAboutLinks neighbour4=new RecordAboutLinks();
			neighbour4.setNeigbourName("ESS5");
			neighbour4.setNeighbourLink(45.0);
			result.add(neighbour4);
			/**
			 * Generators
			 */
			RecordAboutLinks generator1=new RecordAboutLinks();
			generator1.setGeneratorName("Solar");
			/**
			 * Number of consumers
			 */
			RecordAboutLinks consumers=new RecordAboutLinks();
			consumers.setNumberOfConsumers(6);
		}
		
		if (agentName.equals("ESS3")) {
			RecordAboutLinks neighbour1=new RecordAboutLinks();
			neighbour1.setNeigbourName("ESS1");
			neighbour1.setNeighbourLink(120.0);
			result.add(neighbour1);
			
			RecordAboutLinks neighbour2=new RecordAboutLinks();
			neighbour2.setNeigbourName("ESS2");
			neighbour2.setNeighbourLink(80.0);
			result.add(neighbour2);
			
			RecordAboutLinks neighbour3=new RecordAboutLinks();
			neighbour3.setNeigbourName("ESS4");
			neighbour3.setNeighbourLink(98.0);
			result.add(neighbour3);
			
			RecordAboutLinks neighbour4=new RecordAboutLinks();
			neighbour4.setNeigbourName("ESS5");
			neighbour4.setNeighbourLink(65.0);
			result.add(neighbour4);
			/**
			 * Number of consumers
			 */
			RecordAboutLinks consumers=new RecordAboutLinks();
			consumers.setNumberOfConsumers(7);
		}
		
		if (agentName.equals("ESS4")) {
			RecordAboutLinks neighbour1=new RecordAboutLinks();
			neighbour1.setNeigbourName("ESS1");
			neighbour1.setNeighbourLink(38.0);
			result.add(neighbour1);
			
			RecordAboutLinks neighbour2=new RecordAboutLinks();
			neighbour2.setNeigbourName("ESS2");
			neighbour2.setNeighbourLink(38.0);
			result.add(neighbour2);
			
			RecordAboutLinks neighbour3=new RecordAboutLinks();
			neighbour3.setNeigbourName("ESS3");
			neighbour3.setNeighbourLink(98.0);
			result.add(neighbour3);

			RecordAboutLinks neighbour4=new RecordAboutLinks();
			neighbour4.setNeigbourName("ESS5");
			neighbour4.setNeighbourLink(63.0);
			result.add(neighbour4);
			/**
			 * Generators
			 */
			RecordAboutLinks generator1=new RecordAboutLinks();
			generator1.setGeneratorName("Diesel");
			/**
			 * Number of consumers
			 */
			RecordAboutLinks consumers=new RecordAboutLinks();
			consumers.setNumberOfConsumers(14);
		}
		
		if (agentName.equals("ESS5")) {
			RecordAboutLinks neighbour1=new RecordAboutLinks();
			neighbour1.setNeigbourName("ESS1");
			neighbour1.setNeighbourLink(85.0);
			result.add(neighbour1);
			
			RecordAboutLinks neighbour2=new RecordAboutLinks();
			neighbour2.setNeigbourName("ESS2");
			neighbour2.setNeighbourLink(45.0);
			result.add(neighbour2);

			RecordAboutLinks neighbour3=new RecordAboutLinks();
			neighbour3.setNeigbourName("ESS3");
			neighbour3.setNeighbourLink(65.0);
			result.add(neighbour3);

			RecordAboutLinks neighbour4=new RecordAboutLinks();
			neighbour4.setNeigbourName("ESS4");
			neighbour4.setNeighbourLink(63.0);
			result.add(neighbour4);
			/**
			 * Number of consumers
			 */
			RecordAboutLinks consumers=new RecordAboutLinks();
			consumers.setNumberOfConsumers(1);
		}
		return result;
	}

}
