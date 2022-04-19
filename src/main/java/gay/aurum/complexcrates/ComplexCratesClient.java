package gay.aurum.complexcrates;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import xyz.sunrose.simplecrates.CrateRenderer;

public class ComplexCratesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ComplexCrates.BASE_CRATE_BLOCK_ENTITY, CrateRenderer::new);
    }
}
