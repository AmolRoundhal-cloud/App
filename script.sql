USE [iprod_emami_wms_db]
GO
/****** Object:  View [dbo].[iprod_view_infeed_mission_runtime_details]    Script Date: 04-03-2024 17:57:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[iprod_view_infeed_mission_runtime_details]
AS
SELECT        dbo.view_master_pallet_information.PALLET_CODE, dbo.view_master_pallet_information.SKU_CODE, dbo.view_master_pallet_information.BATCH_NO, dbo.view_master_pallet_information.QUANTITY, 
                         dbo.view_master_pallet_information.PRODORDERNO, dbo.iprod_master_sku.SKU_NAME, dbo.iprod_master_sku.PRODUCT_ID, dbo.iprod_master_product.PRODUCT_NAME, dbo.iprod_master_product.PRODUCT_DESCRIPTION,
                          dbo.iprod_master_floor.FLOOR_NAME, dbo.iprod_master_area.AREA_NAME, dbo.iprod_master_area.AREA_DESCRIPTION, dbo.iprod_master_rack.RACK_NAME, dbo.iprod_master_rack.RACK_DESCRIPTION, 
                         dbo.iprod_master_position.POSITION_NAME, dbo.iprod_master_mission_route_details.RACK_ID_START, dbo.iprod_master_mission_route_details.RACK_ID_END, dbo.iprod_master_mission_route_details.LINE_ID, 
                         dbo.iprod_master_line.LINE_NAME, dbo.iprod_master_mission_route_details.LIFTER_ID, dbo.iprod_master_lifter.LIFTER_NAME, dbo.iprod_master_mission_route_details.MOTHER_BABY_SHUTTLE_ID, 
                         dbo.iprod_master_mother_baby_shuttle.MOTHER_BABY_SHUTTLE_NAME, dbo.iprod_infeed_mission_runtime_details.PALLET_INFORMATION_ID, dbo.iprod_infeed_mission_runtime_details.SKU_ID, 
                         dbo.iprod_infeed_mission_runtime_details.FLOOR_ID, dbo.iprod_infeed_mission_runtime_details.AREA_ID, dbo.iprod_infeed_mission_runtime_details.RACK_ID, dbo.iprod_infeed_mission_runtime_details.POSITION_ID, 
                         dbo.iprod_infeed_mission_runtime_details.MISSION_ROUTE_ID, dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_START_DATE, 
                         dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_START_TIME, dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_END_DATE, 
                         dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_END_TIME, dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_STATUS, 
                         dbo.iprod_infeed_mission_runtime_details.MISSION_IS_DELETED, dbo.iprod_infeed_mission_runtime_details.IS_MISSION_REGENERATED, 
                         dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_DETAILS_ID, dbo.iprod_master_position.POSITION_IS_ALLOCATED_FOR_MISSION, dbo.iprod_master_rack.RACK_COLUMN, 
                         dbo.iprod_master_rack.RACK_SIDE, dbo.iprod_master_position.POSITION_ROW, dbo.iprod_infeed_mission_runtime_details.MISSION_RUNTIME_INFEED_STATUS_CC, dbo.iprod_master_pallet_status.PALLET_STATUS_NAME, 
                         dbo.view_master_pallet_information.PALLET_INFORMATION_STATUS_ID
FROM            dbo.iprod_master_product INNER JOIN
                         dbo.iprod_master_sku ON dbo.iprod_master_product.PRODUCT_ID = dbo.iprod_master_sku.PRODUCT_ID INNER JOIN
                         dbo.iprod_infeed_mission_runtime_details ON dbo.iprod_master_sku.SKU_ID = dbo.iprod_infeed_mission_runtime_details.SKU_ID INNER JOIN
                         dbo.iprod_master_area ON dbo.iprod_infeed_mission_runtime_details.AREA_ID = dbo.iprod_master_area.AREA_ID INNER JOIN
                         dbo.iprod_master_floor ON dbo.iprod_infeed_mission_runtime_details.FLOOR_ID = dbo.iprod_master_floor.FLOOR_ID INNER JOIN
                         dbo.iprod_master_rack ON dbo.iprod_infeed_mission_runtime_details.RACK_ID = dbo.iprod_master_rack.RACK_ID INNER JOIN
                         dbo.iprod_master_position ON dbo.iprod_infeed_mission_runtime_details.POSITION_ID = dbo.iprod_master_position.POSITION_ID INNER JOIN
                         dbo.view_master_pallet_information ON dbo.iprod_infeed_mission_runtime_details.PALLET_INFORMATION_ID = dbo.view_master_pallet_information.PALLET_INFORMATION_ID INNER JOIN
                         dbo.iprod_master_line INNER JOIN
                         dbo.iprod_master_mission_route_details ON dbo.iprod_master_line.LINE_ID = dbo.iprod_master_mission_route_details.LINE_ID INNER JOIN
                         dbo.iprod_master_lifter ON dbo.iprod_master_mission_route_details.LIFTER_ID = dbo.iprod_master_lifter.LIFTER_ID INNER JOIN
                         dbo.iprod_master_mother_baby_shuttle ON dbo.iprod_master_mission_route_details.MOTHER_BABY_SHUTTLE_ID = dbo.iprod_master_mother_baby_shuttle.MOTHER_BABY_SHUTTLE_ID ON 
                         dbo.iprod_infeed_mission_runtime_details.MISSION_ROUTE_ID = dbo.iprod_master_mission_route_details.MISSION_ROUTE_ID INNER JOIN
                         dbo.iprod_master_pallet_status ON dbo.view_master_pallet_information.PALLET_INFORMATION_STATUS_ID = dbo.iprod_master_pallet_status.PALLET_STATUS_ID
GO
/****** Object:  View [dbo].[iprod_view_outfeed_mission_runtime_and_mission_route_details]    Script Date: 04-03-2024 17:57:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[iprod_view_outfeed_mission_runtime_and_mission_route_details]
AS
SELECT        dbo.iprod_master_mission_route_details.CONVEYOR_ID, dbo.iprod_master_mission_route_details.LIFTER_ID, dbo.iprod_master_mission_route_details.MOTHER_BABY_SHUTTLE_ID, 
                         dbo.iprod_master_mission_route_details.FLOOR_ID, dbo.iprod_master_mission_route_details.AREA_ID, dbo.iprod_outfeed_mission_runtime_details.PALLET_INFORMATION_ID, 
                         dbo.iprod_outfeed_mission_runtime_details.SKU_ID, dbo.iprod_outfeed_mission_runtime_details.MISSION_ROUTE_ID, dbo.iprod_outfeed_mission_runtime_details.POSITION_ID, 
                         dbo.iprod_outfeed_mission_runtime_details.TRUCK_NUMBER, dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_GENERATE_START_DATE, 
                         dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_GENERATE_START_TIME, dbo.iprod_master_area.AREA_NAME, dbo.iprod_master_floor.FLOOR_NAME, 
                         dbo.iprod_master_conveyor.CONVEYOR_NAME, dbo.iprod_master_mother_baby_shuttle.MOTHER_BABY_SHUTTLE_NAME, 
                         dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_OUTFEED_START_DATE, dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_OUTFEED_START_TIME, 
                         dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_OUTFEED_END_DATE, dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_OUTFEED_END_TIME, 
                         dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_OUTFEED_STATUS, dbo.iprod_outfeed_mission_runtime_details.IS_STACKER_MISSION, 
                         dbo.iprod_outfeed_mission_runtime_details.LOADING_BAY, dbo.iprod_outfeed_mission_runtime_details.MISSION_RUNTIME_OUTFEED_DETAILS_ID, dbo.iprod_master_position.POSITION_NAME, 
                         dbo.view_master_pallet_information.PALLET_CODE, dbo.view_master_pallet_information.SKU_CODE, dbo.view_master_pallet_information.BATCH_NO, dbo.view_master_pallet_information.QUANTITY, 
                         dbo.view_master_pallet_information.PRODORDERNO, dbo.iprod_master_sku.PRODUCT_ID, dbo.iprod_master_sku.SKU_NAME, dbo.iprod_master_position.RACK_ID, 
                         dbo.iprod_outfeed_mission_runtime_details.ZOTB_NUMBER, dbo.iprod_master_product.DISPATCH_SEQUENCE
FROM            dbo.iprod_master_product INNER JOIN
                         dbo.iprod_master_sku ON dbo.iprod_master_product.PRODUCT_ID = dbo.iprod_master_sku.PRODUCT_ID RIGHT OUTER JOIN
                         dbo.iprod_master_mission_route_details INNER JOIN
                         dbo.iprod_outfeed_mission_runtime_details ON dbo.iprod_master_mission_route_details.MISSION_ROUTE_ID = dbo.iprod_outfeed_mission_runtime_details.MISSION_ROUTE_ID INNER JOIN
                         dbo.iprod_master_area ON dbo.iprod_master_mission_route_details.AREA_ID = dbo.iprod_master_area.AREA_ID INNER JOIN
                         dbo.iprod_master_floor ON dbo.iprod_master_mission_route_details.FLOOR_ID = dbo.iprod_master_floor.FLOOR_ID INNER JOIN
                         dbo.iprod_master_mother_baby_shuttle ON dbo.iprod_master_mission_route_details.MOTHER_BABY_SHUTTLE_ID = dbo.iprod_master_mother_baby_shuttle.MOTHER_BABY_SHUTTLE_ID INNER JOIN
                         dbo.iprod_master_conveyor ON dbo.iprod_master_mission_route_details.CONVEYOR_ID = dbo.iprod_master_conveyor.CONVEYOR_ID INNER JOIN
                         dbo.iprod_master_position ON dbo.iprod_outfeed_mission_runtime_details.POSITION_ID = dbo.iprod_master_position.POSITION_ID ON 
                         dbo.iprod_master_sku.SKU_ID = dbo.iprod_outfeed_mission_runtime_details.SKU_ID LEFT OUTER JOIN
                         dbo.view_master_pallet_information ON dbo.iprod_outfeed_mission_runtime_details.PALLET_INFORMATION_ID = dbo.view_master_pallet_information.PALLET_INFORMATION_ID
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[59] 4[4] 2[25] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "iprod_master_product"
            Begin Extent = 
               Top = 309
               Left = 746
               Bottom = 439
               Right = 965
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_sku"
            Begin Extent = 
               Top = 214
               Left = 351
               Bottom = 344
               Right = 551
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_infeed_mission_runtime_details"
            Begin Extent = 
               Top = 14
               Left = 9
               Bottom = 183
               Right = 314
            End
            DisplayFlags = 280
            TopColumn = 10
         End
         Begin Table = "iprod_master_area"
            Begin Extent = 
               Top = 36
               Left = 1247
               Bottom = 166
               Right = 1488
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_floor"
            Begin Extent = 
               Top = 0
               Left = 486
               Bottom = 130
               Right = 735
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_rack"
            Begin Extent = 
               Top = 0
               Left = 899
               Bottom = 130
               Right = 1141
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_position"
            Begin Extent = 
               Top = 141
               Left = 974
       ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'iprod_view_infeed_mission_runtime_details'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'        Bottom = 270
               Right = 1303
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "view_master_pallet_information"
            Begin Extent = 
               Top = 132
               Left = 552
               Bottom = 262
               Right = 921
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_line"
            Begin Extent = 
               Top = 344
               Left = 495
               Bottom = 474
               Right = 685
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_mission_route_details"
            Begin Extent = 
               Top = 404
               Left = 128
               Bottom = 534
               Right = 367
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "iprod_master_lifter"
            Begin Extent = 
               Top = 469
               Left = 983
               Bottom = 599
               Right = 1229
            End
            DisplayFlags = 280
            TopColumn = 5
         End
         Begin Table = "iprod_master_mother_baby_shuttle"
            Begin Extent = 
               Top = 555
               Left = 470
               Bottom = 685
               Right = 816
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_pallet_status"
            Begin Extent = 
               Top = 294
               Left = 1003
               Bottom = 424
               Right = 1253
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 11
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 4425
         Alias = 900
         Table = 5925
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'iprod_view_infeed_mission_runtime_details'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'iprod_view_infeed_mission_runtime_details'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[23] 4[4] 2[51] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = -868
         Left = 0
      End
      Begin Tables = 
         Begin Table = "iprod_master_product"
            Begin Extent = 
               Top = 870
               Left = 382
               Bottom = 1094
               Right = 603
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_sku"
            Begin Extent = 
               Top = 930
               Left = 38
               Bottom = 1122
               Right = 239
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_mission_route_details"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 280
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_outfeed_mission_runtime_details"
            Begin Extent = 
               Top = 138
               Left = 38
               Bottom = 268
               Right = 366
            End
            DisplayFlags = 280
            TopColumn = 12
         End
         Begin Table = "iprod_master_area"
            Begin Extent = 
               Top = 270
               Left = 38
               Bottom = 400
               Right = 281
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_floor"
            Begin Extent = 
               Top = 402
               Left = 38
               Bottom = 532
               Right = 289
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_mother_baby_shuttle"
            Begin Extent = 
               Top = 534
  ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'iprod_view_outfeed_mission_runtime_and_mission_route_details'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'             Left = 38
               Bottom = 664
               Right = 389
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_conveyor"
            Begin Extent = 
               Top = 666
               Left = 38
               Bottom = 796
               Right = 267
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "iprod_master_position"
            Begin Extent = 
               Top = 798
               Left = 38
               Bottom = 928
               Right = 344
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "view_master_pallet_information"
            Begin Extent = 
               Top = 870
               Left = 641
               Bottom = 1000
               Right = 1010
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 4710
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'iprod_view_outfeed_mission_runtime_and_mission_route_details'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'iprod_view_outfeed_mission_runtime_and_mission_route_details'
GO
