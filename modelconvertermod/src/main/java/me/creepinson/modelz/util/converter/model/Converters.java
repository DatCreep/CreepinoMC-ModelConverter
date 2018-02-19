package me.creepinson.modelz.util.converter.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import me.creepinson.modelz.Modelz;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.util.ResourceLocation;

public class Converters {

	public static Gson getGson() {
		return gson;
	}

	private static Gson gson = new Gson();

	public static class JSONToJava {
		private File jsonModel;

		public JSONToJava(File jsonModel) {
			if (jsonModel != null && jsonModel.exists()) {
				this.jsonModel = jsonModel;
			} else {
				Modelz.getLogger().error("JSON model file being imported is null or does not exist!");

			}
		}

		public File getJsonModel() {
			return jsonModel;
		}

		public File convert(String exportedFileName, int texWidth, int texHeight) {
			File javaFile = null;
			if (this.jsonModel != null && this.jsonModel.exists()) {
				/*
				 * try { int numberPart = 1; ModelBlock model =
				 * ModelBlock.deserialize(new FileReader(this.jsonModel));
				 * javaFile = new File(Modelz.getDataFolder(),
				 * exportedFileName+".java");
				 * 
				 * PrintWriter writer = new PrintWriter(javaFile, "UTF-8");
				 * writer.println("import net.minecraft.client.model.ModelBase"
				 * ); writer.
				 * println("import net.minecraft.client.model.ModelRenderer");
				 * for(BlockPart bp : model.getElements()){ for(int f = 0; f <
				 * bp.mapFaces.size(); f++){ BlockPartFace bfp =
				 * bp.mapFaces.get(f);
				 * 
				 * }
				 * 
				 * writer.println("        ModelRenderer modelPart" +
				 * numberPart); numberPart++; } writer.println("public class " +
				 * exportedFileName + " extends ModelBase");
				 * writer.println("{"); writer.println("    public Model()");
				 * writer.println("    {"); writer.println("    }");
				 * writer.println("}"); writer.close(); } catch
				 * (FileNotFoundException e) { e.printStackTrace(); } catch
				 * (UnsupportedEncodingException e) { e.printStackTrace(); }
				 */

				try {
					javaFile = new File(Modelz.getDataFolder(), exportedFileName);
					try {
						javaFile.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ModelBlock newModel = ModelBlock.deserialize(new FileReader(this.jsonModel));
					try {
						BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
								.getResource(new ResourceLocation(newModel.textures.get(0))).getInputStream());

						JavaModel temp = new JavaModel(image.getHeight(), image.getWidth());
						for (BlockPart bp : newModel.getElements()) {
							JavaModelRenderer tempRenderer = new JavaModelRenderer(temp);
							temp.boxList.add(tempRenderer);
						}
						
						FileOutputStream f = new FileOutputStream(javaFile);
						ObjectOutputStream o;
						try {
							o = new ObjectOutputStream(f);

							o.writeObject(temp);

							o.close();
							f.close();
						} catch (IOException e) {
							Modelz.getLogger().error("Error converting Json!");
							e.printStackTrace();
						}

					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					Modelz.getLogger().error("Error converting Json!");
					e.printStackTrace();
				}

				return javaFile;
			}

			return javaFile;
		}

	}
}
