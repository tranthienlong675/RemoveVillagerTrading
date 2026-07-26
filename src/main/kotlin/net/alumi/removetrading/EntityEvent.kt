package net.alumi.removetrading

import net.minecraft.world.entity.npc.Villager
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.ItemCost
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraft.world.item.trading.MerchantOffers
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent
import net.neoforged.neoforge.event.village.VillagerTradesEvent

object EntityEvent {
    private val resultItem = ItemStack(Items.MELON_SLICE)

    fun register(modBus: IEventBus, gameBus: IEventBus) {
        gameBus.addListener(::onVillagerSetProfession)
        gameBus.addListener(::onVillagerJoin)
    }

    fun onVillagerJoin(event: EntityJoinLevelEvent) {
        val villager = event.entity as? Villager ?: return

        val profession = villager.villagerData.profession
        if (profession == VillagerProfession.NONE ||
            profession == VillagerProfession.NITWIT
        ) return

        val offers = villager.offers

        offers.clear()
        offers.add(
            createLockedOffer()
        )
    }
    fun onVillagerSetProfession(event: VillagerTradesEvent) {
        event.trades.values.forEach{ it.clear() }

        event.trades[1].add { _, _ ->
            createLockedOffer()
        }
    }

    private fun createLockedOffer() = MerchantOffer(
        ItemCost(Items.EMERALD, 1),
        resultItem,
        1,
        1,
        0.0f
    )
}