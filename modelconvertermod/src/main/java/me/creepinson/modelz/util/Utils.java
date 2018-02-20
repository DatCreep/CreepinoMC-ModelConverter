package me.creepinson.modelz.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.creepinson.modelz.Modelz;
import me.creepinson.modelz.util.converter.model.JavaModel;
import me.creepinson.modelz.util.converter.model.JavaModelRenderer;

public class Utils {

	public static class ConverterUtils {

		public static StringBuilder sendJavaModelToJavaFile(JavaModel model, String modelName, String author, Object... options) {
			StringBuilder sb = new StringBuilder();

			List<JavaModelRenderer> allCubes = model.boxList;


			sb.append("package " + options[0] + ";\n\n");
			sb.append("import net.minecraft.client.model.ModelBase;\n");
			sb.append("import net.minecraft.client.model.ModelRenderer;\n");
			sb.append("import net.minecraft.entity.Entity;\n");
			sb.append("import net.minecraft.client.renderer.GlStateManager;\n");
			sb.append("import org.lwjgl.opengl.GL11;\n");
			sb.append("\n");
			sb.append("/**\n");
			sb.append(" * " + modelName + " Made By" + author + "\n");
			sb.append(" * Converted With Modelz " + Modelz.VERSION + "\n");
			sb.append(" */\n");
			sb.append(" public class " + modelName + " { \n");
			for (JavaModelRenderer cube : allCubes) {
				sb.append("    public ModelRenderer " + cube.boxName + ";\n");
			}
			sb.append("\n");
			sb.append("    public " + modelName + "() {\n");
			sb.append("        this.textureWidth = " + model.textureWidth + ";\n");
			sb.append("        this.textureHeight = " + model.textureHeight + ";\n");
			for (JavaModelRenderer e : model.boxList) {
				sb.append("        this." + e.boxName + " = new ModelRenderer(this, " + 0 + ", "
						+ 0 + ");\n");
				sb.append("        this." + e.boxName + ".addBox(" + e.from.x + "F, " + e.from.y + "F, "
						+ e.from.z + "F, " + e.to.x + ", " + e.to.y + ", "
						+ e.to.z + ", " + 1 + "F);\n");
			}
			sb.append("    }\n\n");
			sb.append("    @Override\n");
			sb.append(
					"    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { \n");

			for (JavaModelRenderer e : model.boxList) {
				sb.append("        GlStateManager.pushMatrix();\n");
				sb.append("        this." + e.boxName + ".render(f5);\n");
			}
	        sb.append("    }\n");
	        sb.append("}\n");
			

		return sb;
	}

}

}
