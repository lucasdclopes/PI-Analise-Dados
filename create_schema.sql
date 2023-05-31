

USE [YOUR_DATA_BASE_NAME]
GO

/****** Object:  Table [dbo].[annual_co]    Script Date: 5/30/2023 10:37:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[annual_co](
	[year] [smallint] NOT NULL,
	[Annual_CO] [decimal](25, 11) NOT NULL,
	[id_country] [int] NOT NULL,
 CONSTRAINT [CPK__annual_co] PRIMARY KEY CLUSTERED 
(
	[id_country] ASC,
	[year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[countries]    Script Date: 5/30/2023 10:37:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[countries](
	[country_code] [char](3) NOT NULL,
	[country_name] [varchar](60) NOT NULL,
	[sub_region_name] [varchar](31) NOT NULL,
	[income_group] [varchar](31) NOT NULL,
	[Id_Country] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK__Id_Country] PRIMARY KEY CLUSTERED 
(
	[Id_Country] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[countries_gdp]    Script Date: 5/30/2023 10:37:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[countries_gdp](
	[year] [smallint] NOT NULL,
	[total_gdp] [decimal](25, 8) NOT NULL,
	[total_gdp_million] [decimal](25, 15) NOT NULL,
	[gdp_variation] [decimal](20, 16) NOT NULL,
	[id_country] [int] NOT NULL,
 CONSTRAINT [CPK__countries_gdp] PRIMARY KEY CLUSTERED 
(
	[id_country] ASC,
	[year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[population_size]    Script Date: 5/30/2023 10:37:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[population_size](
	[year] [smallint] NOT NULL,
	[population_est] [bigint] NOT NULL,
	[id_country] [int] NOT NULL,
 CONSTRAINT [CPK__population_size] PRIMARY KEY CLUSTERED 
(
	[id_country] ASC,
	[year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[annual_co]  WITH CHECK ADD  CONSTRAINT [FK__annual_co__id_country__countries] FOREIGN KEY([id_country])
REFERENCES [dbo].[countries] ([Id_Country])
GO
ALTER TABLE [dbo].[annual_co] CHECK CONSTRAINT [FK__annual_co__id_country__countries]
GO
ALTER TABLE [dbo].[countries_gdp]  WITH CHECK ADD  CONSTRAINT [FK__countries_gdp__id_country__countries] FOREIGN KEY([id_country])
REFERENCES [dbo].[countries] ([Id_Country])
GO
ALTER TABLE [dbo].[countries_gdp] CHECK CONSTRAINT [FK__countries_gdp__id_country__countries]
GO
ALTER TABLE [dbo].[population_size]  WITH CHECK ADD  CONSTRAINT [FK__population_size__id_country__countries] FOREIGN KEY([id_country])
REFERENCES [dbo].[countries] ([Id_Country])
GO
ALTER TABLE [dbo].[population_size] CHECK CONSTRAINT [FK__population_size__id_country__countries]
GO
