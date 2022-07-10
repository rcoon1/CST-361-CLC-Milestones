package util;

public class FactoryService 
{
	public static enum DTOType{WEATHER,USERS}	
	public FactoryDTO<?> getDTO(DTOType type)
	{
		switch(type)
		{
			case WEATHER:
				return new WeatherDTO();
			case USERS:
				return new UserDTO();
				default:
					return null;
		}
	}
}